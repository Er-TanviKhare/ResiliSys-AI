package com.resilisys.monitor.service;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

import org.springframework.stereotype.Service;

import com.resilisys.monitor.client.FailureInjectorClient;
import com.resilisys.monitor.client.RecoveryClient;
import com.resilisys.monitor.dto.FailureMetrics;
import com.resilisys.monitor.dto.MetricsResponse;
import com.resilisys.monitor.entity.ServiceMetric;
import com.resilisys.monitor.repository.ServiceMetricRepository;
import com.resilisys.monitor.state.RecoveryState;

@Service
public class MonitoringService {

    private final FailureInjectorClient client;
    private final RecoveryClient recoveryClient;
    private final ServiceMetricRepository repository;
    private final RecoveryState recoveryState;
    public MonitoringService(
            FailureInjectorClient client,
            RecoveryClient recoveryClient,
            ServiceMetricRepository repository,
            RecoveryState recoveryState) {

        this.client = client;
        this.recoveryClient = recoveryClient;
        this.repository = repository;
        this.recoveryState = recoveryState;
    }

    public MetricsResponse getMetrics() {

        MemoryMXBean memoryBean =
                ManagementFactory.getMemoryMXBean();

        MemoryUsage heap =
                memoryBean.getHeapMemoryUsage();

        double usedMemory =
                heap.getUsed() / (1024.0 * 1024.0);

        double maxMemory =
                heap.getMax() / (1024.0 * 1024.0);

        double memoryPercentage =
                (usedMemory / maxMemory) * 100;

        String injectorStatus = client.getFailureInjectorStatus();

        if ("DOWN".equals(injectorStatus)) {

        System.out.println("Failure Injector is DOWN");

        if (!recoveryState.isRecoveryTriggered()) {

                recoveryClient.triggerRecovery("Failure Injector");

                recoveryState.setRecoveryTriggered(true);

                System.out.println("Recovery request sent.");
        }

        } else {

        recoveryState.setRecoveryTriggered(false);
        }

        ServiceMetric metric = new ServiceMetric();

        metric.setServiceName("service-monitor");
        FailureMetrics metrics = client.getMetrics();

        metric.setCpuUsage(metrics.getCpu());
        metric.setMemoryUsage(metrics.getMemory());
        metric.setLatency(metrics.getLatency());
        metric.setTimestamp(java.time.LocalDateTime.now());

        repository.save(metric);

        return new MetricsResponse(
                "failure-injector : " + injectorStatus,
                "UP",
                metrics.getCpu(),
                metrics.getMemory(),
                metrics.getLatency()
        );
    }
}