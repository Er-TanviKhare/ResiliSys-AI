package com.resilisys.monitor.dto;

public class MetricsResponse {

    private String service;
    private String status;
    private double cpu;
    private double memory;
    private double latency;

    public MetricsResponse(
            String service,
            String status,
            double cpu,
            double memory,
            double latency) {

        this.service = service;
        this.status = status;
        this.cpu = cpu;
        this.memory = memory;
        this.latency = latency;
    }

    public String getService() {
        return service;
    }

    public String getStatus() {
        return status;
    }

    public double getCpu() {
        return cpu;
    }

    public double getMemory() {
        return memory;
    }

    public double getLatency() {
        return latency;
    }
}