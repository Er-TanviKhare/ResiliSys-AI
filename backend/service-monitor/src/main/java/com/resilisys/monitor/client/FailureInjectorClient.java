package com.resilisys.monitor.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.resilisys.monitor.dto.FailureMetrics;

@Component
public class FailureInjectorClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public String getFailureInjectorStatus() {

        try {

            String response = restTemplate.getForObject(
                    "http://failure-injector:8082/actuator/health",
                    String.class);

            if (response != null && response.contains("\"status\":\"UP\"")) {
                return "UP";
            }

            return "DOWN";

        } catch (Exception e) {

            return "DOWN";
        }
    }

    public FailureMetrics getMetrics() {

        try {

            return restTemplate.getForObject(

                    "http://failure-injector:8082/metrics",

                    FailureMetrics.class

            );

        }

        catch (Exception e) {

            return new FailureMetrics();

        }

    }
    
}