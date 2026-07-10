package com.resilisys.injector.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resilisys.injector.dto.MetricsResponse;

@RestController
public class FailureController {

    private volatile boolean cpuFault = false;
    private volatile boolean latencyFault = false;

    @PostMapping("/inject/cpu")
    public String injectCpu() {

        cpuFault = true;

        new Thread(() -> {

            long endTime = System.currentTimeMillis() + 10000;

            while (System.currentTimeMillis() < endTime) {
                Math.sqrt(Math.random());
            }

            cpuFault = false;

        }).start();

        return "CPU Spike Injected";
    }

    @PostMapping("/inject/latency")
    public String injectLatency() {

        latencyFault = true;

        new Thread(() -> {

            try {
                Thread.sleep(10000);
            } catch (InterruptedException ignored) {
            }

            latencyFault = false;

        }).start();

        return "Latency Injected";
    }

    @GetMapping("/metrics")
    public MetricsResponse metrics() {

        double cpu = cpuFault ? 90 : 10;

        double memory = cpuFault ? 70 : 25;

        double latency = latencyFault ? 250 : 10;

        return new MetricsResponse(cpu, memory, latency);

    }

}