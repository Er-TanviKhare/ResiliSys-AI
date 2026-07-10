package com.resilisys.monitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resilisys.monitor.entity.ServiceMetric;

public interface ServiceMetricRepository
        extends JpaRepository<ServiceMetric, Long> {
}