package com.resilisys.monitor.state;

import org.springframework.stereotype.Component;

@Component
public class RecoveryState {

    private boolean recoveryTriggered = false;

    public boolean isRecoveryTriggered() {
        return recoveryTriggered;
    }

    public void setRecoveryTriggered(boolean recoveryTriggered) {
        this.recoveryTriggered = recoveryTriggered;
    }
}