package com.resilisys.recovery.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resilisys.recovery.dto.RecoveryRequest;
import com.resilisys.recovery.dto.RecoveryResponse;
import com.resilisys.recovery.service.RecoveryService;

@RestController
@RequestMapping("/recovery")
public class RecoveryController {

    private final RecoveryService service;

    public RecoveryController(
            RecoveryService service) {

        this.service = service;
    }

    @PostMapping("/execute")
    public RecoveryResponse execute(

            @RequestBody RecoveryRequest request) {

        return service.executeRecovery(request);
    }
}