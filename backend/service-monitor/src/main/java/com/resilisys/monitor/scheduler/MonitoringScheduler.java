package com.resilisys.monitor.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.resilisys.monitor.service.MonitoringService;

@Component
public class MonitoringScheduler {

    private final MonitoringService monitoringService;

    public MonitoringScheduler(
            MonitoringService monitoringService) {

        this.monitoringService = monitoringService;
    }

    @Scheduled(fixedRate = 5000)
    public void monitorServices() {

        System.out.println("--------------------------------");
        System.out.println("Background Monitoring Running...");
        System.out.println("--------------------------------");

        monitoringService.getMetrics();
    }
}