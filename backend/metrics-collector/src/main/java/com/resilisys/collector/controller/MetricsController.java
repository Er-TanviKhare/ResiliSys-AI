package com.resilisys.collector.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resilisys.collector.dto.FeatureVector;
import com.resilisys.collector.dto.MetricsSummary;
import com.resilisys.collector.dto.RecoveryHistoryResponse;
import com.resilisys.collector.service.MetricsService;

@RestController
@RequestMapping("/metrics")
public class MetricsController {

    private final MetricsService service;

    public MetricsController(MetricsService service) {
        this.service = service;
    }

    @GetMapping("/features")
    public MetricsSummary getFeatures() {
        return service.getSummary();
    }

    @GetMapping("/ai-features")
    public FeatureVector getAiFeatures() {
        return service.getFeatureVector();
    }

    @GetMapping("/prediction")
    public String prediction() {
        return service.getPrediction();
    }

    @GetMapping("/history")
    public List<RecoveryHistoryResponse> history() {
        return service.getRecoveryHistory();
    }
}