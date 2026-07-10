package com.resilisys.collector.dto;

public class MetricsSummary {

    private double avgCpu;
    private double avgMemory;
    private double avgLatency;
    private double maxCpu;
    private long failureCount;

    public MetricsSummary(double avgCpu,
                          double avgMemory,
                          double avgLatency,
                          double maxCpu,
                          long failureCount) {

        this.avgCpu = avgCpu;
        this.avgMemory = avgMemory;
        this.avgLatency = avgLatency;
        this.maxCpu = maxCpu;
        this.failureCount = failureCount;
    }

    public double getAvgCpu() {
        return avgCpu;
    }

    public double getAvgMemory() {
        return avgMemory;
    }

    public double getAvgLatency() {
        return avgLatency;
    }

    public double getMaxCpu() {
        return maxCpu;
    }

    public long getFailureCount() {
        return failureCount;
    }
}