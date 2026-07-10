package com.resilisys.recovery.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.resilisys.recovery.dto.RecoveryRequest;
import com.resilisys.recovery.dto.RecoveryResponse;
import com.resilisys.recovery.entity.RecoveryHistory;
import com.resilisys.recovery.repository.RecoveryHistoryRepository;

@Service
public class RecoveryService {

    private final RecoveryHistoryRepository repository;

    public RecoveryService(RecoveryHistoryRepository repository) {
        this.repository = repository;
    }

    public RecoveryResponse executeRecovery(
            RecoveryRequest request) {

        RecoveryHistory history =
                new RecoveryHistory();

        history.setServiceName(request.getServiceName());

        history.setFailureType(request.getFailureType());

        history.setRecoveryAction("RESTART_SERVICE");

        history.setStatus("SUCCESS");

        history.setTimestamp(LocalDateTime.now());

        repository.save(history);

        return new RecoveryResponse(

                "Recovery Executed",

                "RESTART_SERVICE",

                "SUCCESS"
        );
    }
}