package com.resilisys.collector.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "service_metrics")
public class ServiceMetric {

    @Id
    private Long id;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "cpu_usage")
    private Double cpuUsage;

    @Column(name = "memory_usage")
    private Double memoryUsage;

    @Column(name = "latency")
    private Double latency;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public ServiceMetric() {
    }

    public Long getId() {
        return id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public Double getCpuUsage() {
        return cpuUsage;
    }

    public Double getMemoryUsage() {
        return memoryUsage;
    }

    public Double getLatency() {
        return latency;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setCpuUsage(Double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public void setMemoryUsage(Double memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public void setLatency(Double latency) {
        this.latency = latency;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}