package com.resilisys.monitor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resilisys.monitor.dto.MetricsResponse;
import com.resilisys.monitor.service.MonitoringService;

@RestController
public class MonitorController {

    private final MonitoringService monitoringService;

    public MonitorController(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    @GetMapping("/monitor/health")
    public String health() {
        return "ResiliSys Service Monitor Running";
    }

    @GetMapping("/monitor/metrics")
    public MetricsResponse metrics() {
        return monitoringService.getMetrics();
    }
}