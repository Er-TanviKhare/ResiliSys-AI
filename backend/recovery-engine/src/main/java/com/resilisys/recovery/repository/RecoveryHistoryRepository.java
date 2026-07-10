package com.resilisys.recovery.repository;

import com.resilisys.recovery.entity.RecoveryHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecoveryHistoryRepository
        extends JpaRepository<RecoveryHistory, Long> {
}