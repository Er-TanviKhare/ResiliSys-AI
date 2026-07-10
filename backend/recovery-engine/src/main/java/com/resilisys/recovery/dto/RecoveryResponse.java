package com.resilisys.recovery.dto;

public class RecoveryResponse {

    private String message;
    private String action;
    private String status;

    public RecoveryResponse() {
    }

    public RecoveryResponse(String message,
                            String action,
                            String status) {

        this.message = message;
        this.action = action;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getAction() {
        return action;
    }

    public String getStatus() {
        return status;
    }
}