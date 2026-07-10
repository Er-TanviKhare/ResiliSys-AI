package com.resilisys.collector.dto;

import java.time.LocalDateTime;

public class RecoveryHistoryResponse {

    private String failureType;
    private String recoveryAction;
    private String serviceName;
    private String status;
    private LocalDateTime timestamp;

    public RecoveryHistoryResponse(
            String failureType,
            String recoveryAction,
            String serviceName,
            String status,
            LocalDateTime timestamp) {

        this.failureType = failureType;
        this.recoveryAction = recoveryAction;
        this.serviceName = serviceName;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getFailureType() {
        return failureType;
    }

    public String getRecoveryAction() {
        return recoveryAction;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

}