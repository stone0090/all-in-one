package com.stone0090.aio.service.clients;

import com.google.common.collect.Maps;

import com.stone0090.aio.service.common.ConfigConstants;
import com.stone0090.aio.service.core.system.impl.ConfigServiceImpl;
import io.kubernetes.client.custom.IntOrString;
import io.kubernetes.client.custom.Quantity;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import com.google.common.collect.Lists;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class K8sClient {

    @Autowired
    private ConfigServiceImpl configServiceImpl;

    private volatile ApiClient apiClient;
    private final String NAMESPACE = "aio";

    public ApiClient getApiClient() {
        if (apiClient == null) {
            synchronized (K8sClient.class) {
                if (apiClient == null) {
                    try {
                        String config = configServiceImpl.getValueByKey(ConfigConstants.DEFAULT_K8S_KUBE_CONFIG);
                        if (StringUtils.isEmpty(config)) {
                            apiClient = ClientBuilder.cluster().build();
                        } else {
                            apiClient = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new StringReader(config))).build();
                        }
                        Configuration.setDefaultApiClient(apiClient);
                    } catch (IOException e) {
                        log.error("init k8s client error", e);
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return apiClient;
    }

    public V1Deployment createDeployment(String resourceId) {
        String deploymentName = resourceId.toLowerCase();
        V1PodSpec v1PodSpec = new V1PodSpecBuilder()
                .withContainers(buildContainers(resourceId))
                .withHostIPC(false)
                .withVolumes(Lists.newArrayList())
                .build();
        HashMap<String, String> labels = new HashMap<String, String>() {{
            put("app", "aio-faas-python-basic");
        }};
        V1PodTemplateSpec v1PodTemplateSpec = new V1PodTemplateSpecBuilder()
                .withSpec(v1PodSpec)
                .withMetadata(new V1ObjectMetaBuilder()
                        .withName(deploymentName)
                        .withLabels(labels)
                        .build())
                .build();
        V1DeploymentSpec v1DeploymentSpec = new V1DeploymentSpecBuilder()
                .withReplicas(1)
                .withSelector(new V1LabelSelectorBuilder()
                        .withMatchLabels(labels)
                        .build())
                .withTemplate(v1PodTemplateSpec)
                .build();
        V1Deployment v1Deployment = new V1DeploymentBuilder()
                .withApiVersion("apps/v1")
                .withKind("Deployment")
                .withMetadata(new V1ObjectMetaBuilder()
                        .withName(deploymentName)
                        .withNamespace(NAMESPACE)
                        .withLabels(labels)
                        .build())
                .withSpec(v1DeploymentSpec)
                .build();
        AppsV1Api apiInstance = new AppsV1Api(this.getApiClient());
        try {
            return apiInstance.createNamespacedDeployment(NAMESPACE, v1Deployment, null, null, null);
        } catch (Exception e) {
            log.error("create deployment error", e);
            throw new RuntimeException(e);
        }
    }

    private List<V1Container> buildContainers(String bizId) {
        List<V1Container> containers = Lists.newArrayList();
        V1EnvVar v1EnvVar = new V1EnvVarBuilder()
                .withName("backend_bizId")
                .withValue(bizId.toLowerCase())
                .build();
        V1ContainerPort v1ContainerPort = new V1ContainerPortBuilder()
                .withContainerPort(6000)
                .build();
        Map<String, Quantity> requests = Maps.newHashMap();
        requests.put("cpu", new Quantity("0.025"));
        requests.put("memory", new Quantity("100Mi"));
        V1ResourceRequirements v1ResourceRequirements = new V1ResourceRequirementsBuilder()
                .withRequests(requests)
                .build();
        V1Container container = new V1ContainerBuilder()
                .withName("aio-faas-python-basic")
                .withImage("shi0090/aio-faas-python-basic:1.0.0")
                .withImagePullPolicy("Never")
                .withEnv(Lists.newArrayList(v1EnvVar))
                .withPorts(Lists.newArrayList(v1ContainerPort))
                .withResources(v1ResourceRequirements)
                .withVolumeMounts(Lists.newArrayList())
                .build();
        containers.add(container);
        return containers;
    }

    public V1Service createService(String bizId) {
        HashMap<String, String> labels = new HashMap<String, String>() {{
            put("app", "aio-faas-python-basic");
        }};
        V1ServicePort v1ServicePort1 = new V1ServicePortBuilder()
                .withPort(6000)
                .withName("scheduler")
                .withTargetPort(new IntOrString(6000))
                .build();
        V1ServicePort v1ServicePort2 = new V1ServicePortBuilder()
                .withPort(6001)
                .withName("operator")
                .withTargetPort(new IntOrString(6001))
                .build();
        V1ServiceSpec v1ServiceSpec = new V1ServiceSpecBuilder()
                .withPorts(v1ServicePort1, v1ServicePort2)
                .withSelector(labels)
                .withType("NodePort")
                .build();
        V1Service v1Service = new V1ServiceBuilder()
                .withApiVersion("v1")
                .withKind("Service")
                .withMetadata(new V1ObjectMetaBuilder()
                        .withName(bizId.toLowerCase() + "-svc")
                        .withNamespace(NAMESPACE)
                        .build())
                .withSpec(v1ServiceSpec)
                .build();
        CoreV1Api coreV1Api = new CoreV1Api(this.getApiClient());
        try {
            return coreV1Api.createNamespacedService(NAMESPACE, v1Service, null, null, null);
        } catch (Exception e) {
            log.error("create service error", e);
            throw new RuntimeException(e);
        }
    }

    public void deleteDeployment(String bizId) {
        String deploymentName = bizId.toLowerCase();
        AppsV1Api apiInstance = new AppsV1Api(this.getApiClient());
        try {
            apiInstance.deleteNamespacedDeployment(deploymentName, NAMESPACE, null, null, null, null, null, null);
        } catch (Exception e) {
            log.error("delete deployment error", e);
            throw new RuntimeException(e);
        }
    }

    public void deleteService(String bizId) {
        String serviceName = bizId.toLowerCase() + "-svc";
        CoreV1Api coreV1Api = new CoreV1Api(this.getApiClient());
        try {
            coreV1Api.deleteNamespacedService(serviceName, NAMESPACE, null, null, null, null, null, null);
        } catch (Exception e) {
            log.error("delete service error", e);
            throw new RuntimeException(e);
        }
    }

}
