package com.resilisys.collector.repository;

import com.resilisys.collector.entity.ServiceMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceMetricRepository extends JpaRepository<ServiceMetric, Long> {

    List<ServiceMetric> findTop20ByOrderByTimestampDesc();
    List<ServiceMetric> findTop5ByOrderByTimestampDesc();

}