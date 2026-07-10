package com.resilisys.collector.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.resilisys.collector.dto.FeatureVector;

@Component
public class AiPredictorClient {

    private final RestTemplate restTemplate;

    public AiPredictorClient(RestTemplate restTemplate){

        this.restTemplate = restTemplate;

    }
    public String predictFailure(FeatureVector featureVector) {

        String url = "http://ai-predictor:8000/predict";

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<FeatureVector> entity =
                new HttpEntity<>(featureVector, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(
                        url,
                        entity,
                        String.class
                );

        return response.getBody();

    }

}