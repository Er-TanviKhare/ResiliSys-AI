package com.resilisys.collector.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RecoveryEngineClient {

    private final RestTemplate restTemplate;

    public RecoveryEngineClient(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;

    }

    public void triggerRecovery() {

        String url = "http://recovery-engine:8083/recovery/execute";

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String,Object> request = new HashMap<>();

        request.put("serviceName","failure-injector");
        request.put("failureType","AI_PREDICTED_FAILURE");

        HttpEntity<Map<String,Object>> entity =
                new HttpEntity<>(request,headers);

        restTemplate.postForEntity(
                url,
                entity,
                String.class
        );

    }

}