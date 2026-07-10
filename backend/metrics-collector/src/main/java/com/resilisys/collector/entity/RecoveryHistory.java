package com.resilisys.collector.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recovery_history")
public class RecoveryHistory {

    @Id
    private Long id;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "failure_type")
    private String failureType;

    @Column(name = "recovery_action")
    private String recoveryAction;

    @Column(name = "status")
    private String status;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public RecoveryHistory() {
    }

    public Long getId() {
        return id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getFailureType() {
        return failureType;
    }

    public String getRecoveryAction() {
        return recoveryAction;
    }

    public String getStatus() {
        return status;
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

    public void setFailureType(String failureType) {
        this.failureType = failureType;
    }

    public void setRecoveryAction(String recoveryAction) {
        this.recoveryAction = recoveryAction;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}