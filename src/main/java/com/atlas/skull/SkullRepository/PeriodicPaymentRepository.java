package com.atlas.skull.SkullRepository;

import com.atlas.skull.SkullModel.PeriodicPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeriodicPaymentRepository extends JpaRepository<PeriodicPayment, Long> {

    List<PeriodicPayment> findAllByStudentPeriodicPayments_Student_Id(Long studentPeriodicPayments_student_id);
}
