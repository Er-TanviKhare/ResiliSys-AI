package com.resilisys.collector.repository;

import com.resilisys.collector.entity.RecoveryHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface RecoveryHistoryRepository extends JpaRepository<RecoveryHistory, Long> {

    long count();
    List<RecoveryHistory> findTop10ByOrderByTimestampDesc();
}