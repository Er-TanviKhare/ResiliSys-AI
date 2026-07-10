package com.resilisys.monitor.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.resilisys.monitor.dto.RecoveryRequest;

@Component
public class RecoveryClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public void triggerRecovery(String serviceName) {

        String url = "http://recovery-engine:8083/recovery/execute";

        RecoveryRequest request = new RecoveryRequest();
        request.setServiceName(serviceName);
        request.setFailureType("SERVICE_DOWN");

        try {

            restTemplate.postForObject(
                    url,
                    request,
                    String.class);

            System.out.println(
                    "Recovery Engine Triggered Successfully");

        } catch (Exception e) {

            System.out.println(
                    "Recovery Engine unavailable");

            e.printStackTrace();
        }
    }
}