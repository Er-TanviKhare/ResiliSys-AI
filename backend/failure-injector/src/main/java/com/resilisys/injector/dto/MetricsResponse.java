package com.resilisys.injector.dto;

public class MetricsResponse {

    private double cpu;
    private double memory;
    private double latency;

    public MetricsResponse(double cpu, double memory, double latency) {
        this.cpu = cpu;
        this.memory = memory;
        this.latency = latency;
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