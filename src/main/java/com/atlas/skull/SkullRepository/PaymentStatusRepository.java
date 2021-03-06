package com.atlas.skull.SkullRepository;

import com.atlas.skull.SkullModel.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentStatusRepository extends JpaRepository<PaymentStatus, Long> {
}
