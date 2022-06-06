package com.atlas.skull.SkullRepository;

import com.atlas.skull.SkullModel.RegisteredPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredPaymentRepository extends JpaRepository<RegisteredPayment, Long>  {
}
