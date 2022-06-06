package com.atlas.skull.SkullRepository;

import com.atlas.skull.SkullModel.PaymentPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentPeriodRepository extends JpaRepository<PaymentPeriod, Long> {
}
