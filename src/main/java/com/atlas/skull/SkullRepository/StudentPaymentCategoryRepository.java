package com.atlas.skull.SkullRepository;

import com.atlas.skull.SkullModel.StudentPaymentCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentPaymentCategoryRepository extends JpaRepository<StudentPaymentCategory, Long> {

}
