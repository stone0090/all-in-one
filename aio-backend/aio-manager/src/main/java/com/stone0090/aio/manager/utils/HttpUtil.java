package com.stone0090.aio.manager.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@DependsOn("restTemplate")
public class HttpUtil {

    @Autowired
    private RestTemplate restTemplate;

    public String get(String url) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.getForObject(addHttpPrefix(url), String.class, entity);
    }

    public String post(String url, String body) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        return restTemplate.postForObject(addHttpPrefix(url), entity, String.class);
    }

    private String addHttpPrefix(String url) {
        if (url.startsWith("http://") || url.startsWith("https://")) {
            return url;
        }
        return "http://" + url;
    }

}
