package com.resilisys.collector.service;

import java.time.LocalDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Service;

import com.resilisys.collector.client.AiPredictorClient;
import com.resilisys.collector.dto.FeatureVector;
import com.resilisys.collector.dto.MetricsSummary;
import com.resilisys.collector.entity.ServiceMetric;
import com.resilisys.collector.repository.RecoveryHistoryRepository;
import com.resilisys.collector.repository.ServiceMetricRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resilisys.collector.client.RecoveryEngineClient;
import com.resilisys.collector.dto.RecoveryHistoryResponse;

@Service
public class MetricsService {

        private final ServiceMetricRepository metricRepository;
        private final RecoveryHistoryRepository recoveryRepository;
        private final AiPredictorClient aiPredictorClient;
        private final RecoveryEngineClient recoveryEngineClient;

        public MetricsService(

                ServiceMetricRepository metricRepository,

                RecoveryHistoryRepository recoveryRepository,

                AiPredictorClient aiPredictorClient,

                RecoveryEngineClient recoveryEngineClient) {

        this.metricRepository = metricRepository;

        this.recoveryRepository = recoveryRepository;

        this.aiPredictorClient = aiPredictorClient;

        this.recoveryEngineClient = recoveryEngineClient;

        }

    public MetricsSummary getSummary() {

        List<ServiceMetric> metrics =
                metricRepository.findTop5ByOrderByTimestampDesc();

        if (metrics.isEmpty()) {
            return new MetricsSummary(0,0,0,0,0);
        }

        DoubleSummaryStatistics cpu =
                metrics.stream()
                        .mapToDouble(ServiceMetric::getCpuUsage)
                        .summaryStatistics();

        DoubleSummaryStatistics memory =
                metrics.stream()
                        .mapToDouble(ServiceMetric::getMemoryUsage)
                        .summaryStatistics();

        DoubleSummaryStatistics latency =
                metrics.stream()
                        .mapToDouble(ServiceMetric::getLatency)
                        .summaryStatistics();

        return new MetricsSummary(

                round(cpu.getAverage()),
                round(memory.getAverage()),
                round(latency.getAverage()),
                round(cpu.getMax()),
                recoveryRepository.count()

        );
    }

    public FeatureVector getFeatureVector() {

        List<ServiceMetric> metrics =
                metricRepository.findTop5ByOrderByTimestampDesc();

        if(metrics.isEmpty()){

            return new FeatureVector(

                    LocalDateTime.now().toString(),

                    0,

                    0,

                    "STABLE",

                    0,

                    "STABLE",

                    0,

                    0,

                    0
            );
        }

        DoubleSummaryStatistics cpu =
                metrics.stream()
                        .mapToDouble(ServiceMetric::getCpuUsage)
                        .summaryStatistics();

        DoubleSummaryStatistics memory =
                metrics.stream()
                        .mapToDouble(ServiceMetric::getMemoryUsage)
                        .summaryStatistics();

        DoubleSummaryStatistics latency =
                metrics.stream()
                        .mapToDouble(ServiceMetric::getLatency)
                        .summaryStatistics();

        double avgCpu = round(cpu.getAverage());
        double maxCpu = round(cpu.getMax());

        double avgMemory = round(memory.getAverage());

        double avgLatency = round(latency.getAverage());

        long failures = recoveryRepository.count();

        String cpuTrend =
                avgCpu > 70 ? "INCREASING" : "STABLE";

        String memoryTrend =
                avgMemory > 80 ? "INCREASING" : "STABLE";

        int riskScore = 0;

        if(maxCpu > 80)
                riskScore += 40;

        if(avgCpu > 50)
                riskScore += 20;

        if(avgMemory > 70)
                riskScore += 20;

        if(avgLatency > 100)
                riskScore += 10;

        if(failures > 3)
                riskScore += 10;

        return new FeatureVector(

                LocalDateTime.now().toString(),

                avgCpu,

                maxCpu,

                cpuTrend,

                avgMemory,

                memoryTrend,

                avgLatency,

                failures,

                riskScore

        );
    }

    private double round(double value){
        return Math.round(value*100.0)/100.0;
    }

        public String getPrediction() {

        try {

                FeatureVector featureVector = getFeatureVector();

                String response =
                        aiPredictorClient.predictFailure(featureVector);

                ObjectMapper mapper =
                        new ObjectMapper();

                JsonNode json =
                        mapper.readTree(response);

                int prediction =
                        json.get("prediction").asInt();

                if(prediction == 1){

                recoveryEngineClient.triggerRecovery();

                System.out.println(
                        "AI triggered Recovery Engine."
                );

                }

                return response;

        }

        catch(Exception e){

                e.printStackTrace();

                return "Prediction Failed";

        }

        }

        public List<RecoveryHistoryResponse> getRecoveryHistory() {

        return recoveryRepository
                .findTop10ByOrderByTimestampDesc()
                .stream()
                .map(r -> new RecoveryHistoryResponse(

                        r.getFailureType(),
                        r.getRecoveryAction(),
                        r.getServiceName(),
                        r.getStatus(),
                        r.getTimestamp()

                ))
                .toList();

        }

}