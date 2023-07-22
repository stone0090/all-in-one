package com.stone0090.aio.service.core.algorithm.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.stone0090.aio.dao.mybatis.entity.ApiDO;
import com.stone0090.aio.dao.mybatis.entity.OperatorDO;
import com.stone0090.aio.dao.mybatis.entity.OperatorDOExample;
import com.stone0090.aio.dao.mybatis.entity.SystemConfigDO;
import com.stone0090.aio.dao.mybatis.mapper.OperatorDOMapper;
import com.stone0090.aio.manager.utils.HttpUtil;
import com.stone0090.aio.manager.utils.UuidUtil;
import com.stone0090.aio.service.common.ConfigConstants;
import com.stone0090.aio.service.common.Converter;
import com.stone0090.aio.service.core.algorithm.ApiService;
import com.stone0090.aio.service.core.algorithm.OperatorService;
import com.stone0090.aio.service.core.system.impl.ConfigServiceImpl;
import com.stone0090.aio.service.enums.AlgoTypeEnum;
import com.stone0090.aio.service.enums.ApiStatusEnum;
import com.stone0090.aio.service.enums.DataTypeEnum;
import com.stone0090.aio.service.enums.InvokeTypeEnum;
import com.stone0090.aio.service.model.web.protocal.PageRequest;
import com.stone0090.aio.service.model.web.protocal.PageResult;
import com.stone0090.aio.service.model.web.request.*;
import com.stone0090.aio.service.model.web.response.OperatorVO;
import org.jose4j.json.JsonUtil;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author stone
 * @date 2023/06/22
 */
@Service("operatorService")
public class OperatorServiceImpl implements OperatorService, ApiService {

    @Autowired
    private OperatorDOMapper operatorDOMapper;
    @Autowired
    private ConfigServiceImpl configServiceImpl;
    @Autowired
    private ApiServiceImpl apiServiceImpl;
    @Autowired
    private HttpUtil httpUtil;

    @Value("${spring.profiles.active}")
    private String env;

    @Override
    public PageResult<OperatorVO> list(OperatorQueryRequest queryRequest, PageRequest pageRequest) {
        OperatorDOExample example = new OperatorDOExample();
        OperatorDOExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(0);
        if (!StringUtils.isEmpty(queryRequest.getOpCode())) {
            criteria.andOpCodeLike("%" + queryRequest.getOpCode() + "%");
        }
        if (!StringUtils.isEmpty(queryRequest.getOpName())) {
            criteria.andOpNameLike("%" + queryRequest.getOpName() + "%");
        }
        PageHelper.startPage(pageRequest.getCurrent(), pageRequest.getPageSize());
        List<OperatorDO> result = operatorDOMapper.selectByExample(example);
        List<ApiDO> apiDOList = apiServiceImpl.listApiByTypeIds(AlgoTypeEnum.OPERATOR.name(),
                result.stream().map(OperatorDO::getId).collect(Collectors.toList()));
        Map<Integer, ApiDO> apiMap = apiDOList.stream()
                .collect(Collectors.toMap(ApiDO::getTypeId, apiDO -> apiDO));
        PageResult<OperatorVO> pageResult = PageResult.buildPageResult(result);
        pageResult.setList(result.stream().map(operatorDO -> Converter.toOperatorVO(operatorDO, apiMap))
                .collect(Collectors.toList()));
        return pageResult;
    }

    @Override
    public OperatorVO get(IdRequest request) {
        OperatorDO result = getOperatorDO(request.getId());
        return result != null ? Converter.toOperatorVO(result) : null;
    }

    @Override
    public int save(OperatorSaveRequest request) {
        checkSaveParam(request.getInputParam(), true);
        checkSaveParam(request.getOutputParam(), false);
        OperatorDO data = Converter.toOperatorDO(request);
        if (data.getId() == null) {
            data.setAlgoPath("");
            return operatorDOMapper.insertSelective(data);
        } else {
            data.setGmtModified(new Date());
            return operatorDOMapper.updateByPrimaryKeySelective(data);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int remove(IdRequest request) {
        ApiDO apiDO = apiServiceImpl.getApiByTypeId(AlgoTypeEnum.OPERATOR.name(), request.getId());
        if (apiDO != null) {
            if (ApiStatusEnum.ONLINE.name().equals(apiDO.getStatus())) {
                throw new RuntimeException("算子删除失败，API状态为在线的算子不可删除！");
            }
            apiServiceImpl.removeApiByTypeId(AlgoTypeEnum.OPERATOR.name(), request.getId());
        }
        OperatorDO data = new OperatorDO();
        data.setId(request.getId());
        data.setIsDeleted((int) System.currentTimeMillis());
        data.setGmtModified(new Date());
        return operatorDOMapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public OperatorVO getDefaultConfig() {
        List<SystemConfigDO> result = configServiceImpl.getByKeys(Lists.newArrayList(
                ConfigConstants.ALGO_CODE_TEMPLATE,
                ConfigConstants.INPUT_PARAM_TEMPLATE,
                ConfigConstants.OUTPUT_PARAM_TEMPLATE));
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }
        OperatorVO operatorVO = new OperatorVO();
        result.forEach(configVO -> {
            if (ConfigConstants.ALGO_CODE_TEMPLATE.equals(configVO.getConfigKey())) {
                operatorVO.setAlgoCode(configVO.getConfigValue());
            } else if (ConfigConstants.INPUT_PARAM_TEMPLATE.equals(configVO.getConfigKey())) {
                operatorVO.setInputParam(configVO.getConfigValue());
            } else if (ConfigConstants.OUTPUT_PARAM_TEMPLATE.equals(configVO.getConfigKey())) {
                operatorVO.setOutputParam(configVO.getConfigValue());
            }
        });
        return operatorVO;
    }

    @Override
    public int onlineApi(ApiRequest request) {
        if (!AlgoTypeEnum.OPERATOR.name().equals(request.getApiType())) {
            throw new RuntimeException("API上线失败，类型不正确！");
        }
        OperatorDO operatorDO = getOperatorDO(request.getId());
        if (operatorDO == null) {
            throw new RuntimeException("API上线失败，算子不存在！");
        }
        ApiDO apiDO = buildOperatorApiDO(operatorDO);
        return apiServiceImpl.online(apiDO, (deployInfo) -> {
            deployInfo.put("algoCode", operatorDO.getAlgoCode());
        });
    }

    @Override
    public int offlineApi(ApiRequest request) {
        if (!AlgoTypeEnum.OPERATOR.name().equals(request.getApiType())) {
            throw new RuntimeException("API下线失败，类型不正确！");
        }
        return apiServiceImpl.offline(AlgoTypeEnum.OPERATOR.name(), request.getId());
    }

    @Override
    public String invokeApi(ApiInvokeRequest request) {
        if (!AlgoTypeEnum.OPERATOR.name().equals(request.getApiType())) {
            throw new RuntimeException("API调用失败，类型不正确！");
        }
        OperatorDO operatorDO = getOperatorDO(request.getId());
        if (operatorDO == null) {
            throw new RuntimeException("API调用失败，算子不存在！");
        }
        return apiServiceImpl.invoke(AlgoTypeEnum.OPERATOR.name(), request.getId(), request.getInputParam());
    }

    public OperatorDO getOperatorDO(Integer id) {
        OperatorDOExample example = new OperatorDOExample();
        example.createCriteria().andIsDeletedEqualTo(0).andIdEqualTo(id);
        List<OperatorDO> result = operatorDOMapper.selectByExample(example);
        return result.size() > 0 ? result.get(0) : null;
    }

    private void checkSaveParam(String param, boolean isInput) {
        String paramType = isInput ? "入参" : "出参";
        try {
            Map<String, Object> inputParamMap = JsonUtil.parseJson(param);
            inputParamMap.forEach((k, v) -> {
                Map<String, Object> inputParamDetailMap = (Map<String, Object>) v;
                if (inputParamDetailMap.get("name") == null) {
                    throw new RuntimeException("算子保存失败，算子" + paramType + "[" + k + "]的格式有误，属性[name]不能为空！");
                } else {
                    if (!(inputParamDetailMap.get("name") instanceof String)) {
                        throw new RuntimeException("算子保存失败，算子" + paramType + "[" + k + "]的格式有误，属性[name]必须为字符串！");
                    }
                }
                if (inputParamDetailMap.get("type") == null) {
                    throw new RuntimeException("算子保存失败，算子" + paramType + "[" + k + "]的格式有误，属性[type]不能为空！");
                } else {
                    String type = inputParamDetailMap.get("type").toString();
                    if (DataTypeEnum.getByCode(type) == null) {
                        throw new RuntimeException("算子保存失败，算子" + paramType + "[" + k + "]的格式有误，属性[type]只能是\"int/double/string/boolean\"其中一个！");
                    }
                }
                if (isInput) {
                    if (inputParamDetailMap.get("required") == null) {
                        throw new RuntimeException("算子保存失败，算子" + paramType + "[" + k + "]的格式有误，属性[required]不能为空！");
                    } else {
                        if (!(inputParamDetailMap.get("required") instanceof Boolean)) {
                            throw new RuntimeException("算子保存失败，算子" + paramType + "[" + k + "]的格式有误，属性[required]只能是true/false！");
                        }
                    }
                }
            });
        } catch (JoseException e) {
            throw new RuntimeException("算子保存失败，算子" + paramType + "格式有误！");
        }
    }

    public ApiDO buildOperatorApiDO(OperatorDO operatorDO) {
        ApiDO apiDO = new ApiDO();
        apiDO.setApiUuid(UuidUtil.getUuid());
        apiDO.setApiName(operatorDO.getOpName());
        apiDO.setApiType(AlgoTypeEnum.OPERATOR.name());
        apiDO.setTypeId(operatorDO.getId());
        apiDO.setInputParam(operatorDO.getInputParam());
        apiDO.setOutputParam(operatorDO.getOutputParam());
        apiDO.setInvokeType(InvokeTypeEnum.SYNC.name());
        apiDO.setApiUrl("");
        apiDO.setCallbackUrl("");
        apiDO.setStatus(ApiStatusEnum.OFFLINE.name());
        return apiDO;
    }

}
