package com.stone0090.aio.service.clients;

import com.google.common.collect.Maps;

import com.stone0090.aio.service.common.ConfigConstants;
import com.stone0090.aio.service.system.impl.ConfigServiceImpl;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class K8sClient {

    @Autowired
    private ConfigServiceImpl configServiceImpl;

    private ApiClient apiClient;
    private final String NAMESPACE = "aio";

    @PostConstruct
    public void init() {
        try {
            String config = configServiceImpl.getValueByKey(ConfigConstants.DEFAULT_K8S_KUBE_CONFIG);
            apiClient = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new StringReader(config))).build();
//            apiClient = ClientBuilder.cluster().build();
            Configuration.setDefaultApiClient(apiClient);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public V1Deployment createDeployment(String bizId) {
        String deploymentName = bizId.toLowerCase();
        V1PodSpec v1PodSpec = new V1PodSpecBuilder()
                .withContainers(buildContainers(bizId))
                .withHostIPC(false)
                .withVolumes(Lists.newArrayList()) // TODO
                .build();
        HashMap<String, String> labels = new HashMap<String, String>() {{
            put("app", "faas-python-basic");
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
        AppsV1Api apiInstance = new AppsV1Api(apiClient);
        try {
            return apiInstance.createNamespacedDeployment(NAMESPACE, v1Deployment, null, null, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<V1Container> buildContainers(String bizId) {
        List<V1Container> containers = Lists.newArrayList();
        V1EnvVar v1EnvVar = new V1EnvVarBuilder()
                .withName("bizId")
                .withValue(bizId.toLowerCase())
                .build();
        V1ContainerPort v1ContainerPort = new V1ContainerPortBuilder()
                .withContainerPort(6001)
                .build();
        Map<String, Quantity> requests = Maps.newHashMap();
        requests.put("cpu", new Quantity("0.025"));
        requests.put("memory", new Quantity("100Mi"));
        V1ResourceRequirements v1ResourceRequirements = new V1ResourceRequirementsBuilder()
                .withRequests(requests)
                .build();
        V1Container container = new V1ContainerBuilder()
                .withName("faas-python-basic")
                .withImage("faas-python-basic:0.0.1")
                .withImagePullPolicy("Never")
                .withEnv(Lists.newArrayList(v1EnvVar))
                .withPorts(Lists.newArrayList(v1ContainerPort))
                .withResources(v1ResourceRequirements)
                .withVolumeMounts(Lists.newArrayList()) // TODO
                .build();
        containers.add(container);
        return containers;
    }

    public V1Service createService(String bizId) {
        HashMap<String, String> labels = new HashMap<String, String>() {{
            put("app", "faas-python-basic");
        }};
        V1ServicePort v1ServicePort = new V1ServicePortBuilder()
                .withPort(6001)
                .withTargetPort(new IntOrString(6001))
                .build();
        V1ServiceSpec v1ServiceSpec = new V1ServiceSpecBuilder()
                .withPorts(v1ServicePort)
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
        CoreV1Api coreV1Api = new CoreV1Api(apiClient);
        try {
            return coreV1Api.createNamespacedService(NAMESPACE, v1Service, null, null, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
