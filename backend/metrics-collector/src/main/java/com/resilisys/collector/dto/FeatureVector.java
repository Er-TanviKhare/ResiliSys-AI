package com.resilisys.collector.dto;

public class FeatureVector {

    private String timestamp;

    private double avgCpu;

    private double maxCpu;

    private String cpuTrend;

    private double avgMemory;

    private String memoryTrend;

    private double avgLatency;

    private long failureCount;

    private int riskScore;

    public FeatureVector(String timestamp,
                         double avgCpu,
                         double maxCpu,
                         String cpuTrend,
                         double avgMemory,
                         String memoryTrend,
                         double avgLatency,
                         long failureCount,
                         int riskScore) {

        this.timestamp = timestamp;
        this.avgCpu = avgCpu;
        this.maxCpu = maxCpu;
        this.cpuTrend = cpuTrend;
        this.avgMemory = avgMemory;
        this.memoryTrend = memoryTrend;
        this.avgLatency = avgLatency;
        this.failureCount = failureCount;
        this.riskScore = riskScore;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public double getAvgCpu() {
        return avgCpu;
    }

    public double getMaxCpu() {
        return maxCpu;
    }

    public String getCpuTrend() {
        return cpuTrend;
    }

    public double getAvgMemory() {
        return avgMemory;
    }

    public String getMemoryTrend() {
        return memoryTrend;
    }

    public double getAvgLatency() {
        return avgLatency;
    }

    public long getFailureCount() {
        return failureCount;
    }

    public int getRiskScore() {
        return riskScore;
    }
}