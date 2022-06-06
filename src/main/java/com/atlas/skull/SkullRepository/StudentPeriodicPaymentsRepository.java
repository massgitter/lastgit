package com.atlas.skull.SkullRepository;

import com.atlas.skull.SkullModel.StudentPeriodicPayments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentPeriodicPaymentsRepository extends JpaRepository<StudentPeriodicPayments, Long> {
}
