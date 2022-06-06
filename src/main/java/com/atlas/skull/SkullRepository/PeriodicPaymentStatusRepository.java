package com.atlas.skull.SkullRepository;

import com.atlas.skull.SkullModel.PeriodicPaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodicPaymentStatusRepository extends JpaRepository<PeriodicPaymentStatus, Long> {
}
