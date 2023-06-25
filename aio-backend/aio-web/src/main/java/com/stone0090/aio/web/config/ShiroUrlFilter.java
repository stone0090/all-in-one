package com.stone0090.aio.web.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stone0090.aio.api.protocal.PageRequest;
import com.stone0090.aio.api.protocal.PageResult;
import com.stone0090.aio.api.request.PermissionQueryRequest;
import com.stone0090.aio.api.response.PermissionVO;
import com.stone0090.aio.api.response.RoleVO;
import com.stone0090.aio.api.response.UserDetailVO;
import com.stone0090.aio.manager.utils.SpringUtil;
import com.stone0090.aio.service.user.PermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 * url权限集中判断过滤器
 *
 * @author stone
 * @date 2021/08/01
 */
public class ShiroUrlFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
        throws Exception {

        try {
            Subject subject = SecurityUtils.getSubject();
            UserDetailVO userDetailVO = (UserDetailVO)subject.getPrincipal();
            if (userDetailVO == null) {
                throw new RuntimeException("您的登陆状态已失效，请重新登陆");
            }

            List<RoleVO> roleVOList = userDetailVO.getRoles();
            if (CollectionUtils.isEmpty(roleVOList)) {
                throw new RuntimeException("您的账号暂未绑定任何角色");
            }

            List<String> permissionUrlList = new ArrayList<>();
            roleVOList.forEach(roleVO -> {
                List<PermissionVO> permissionVOList = roleVO.getPermissions();
                if (!CollectionUtils.isEmpty(permissionVOList)) {
                    permissionVOList.forEach(permissionVO -> {
                        permissionUrlList.add(permissionVO.getPermissionUrl());
                    });
                }
            });
            if (CollectionUtils.isEmpty(permissionUrlList)) {
                throw new RuntimeException("您的角色尚未绑定任何权限");
            }

            String requestURI = getPathWithinApplication(request);
            if (!permissionUrlList.contains(requestURI)) {
                PermissionService permissionService = (PermissionService) SpringUtil.getBean("permissionServiceImpl");
                PermissionQueryRequest queryRequest = new PermissionQueryRequest();
                queryRequest.setPermissionUrl(requestURI);
                PageResult<PermissionVO> pageResult = permissionService.list(queryRequest, new PageRequest());
                if (pageResult.getTotal() > 0) {
                    throw new RuntimeException("您无权访问接口：" + requestURI);
                }
            }

        } catch (Exception e) {
            HandlerExceptionResolver resolver = (HandlerExceptionResolver) SpringUtil.getBean("handlerExceptionResolver");
            resolver.resolveException((HttpServletRequest)request, (HttpServletResponse)response, null, e);
            return false;
        }

        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return false;
    }

}