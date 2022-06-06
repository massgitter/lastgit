package com.atlas.skull.SkullRepository;

import com.atlas.skull.Dto.StudentCategoryResponse;
import com.atlas.skull.SkullModel.StudentCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCategoryRepository extends JpaRepository<StudentCategory, Long> {

    StudentCategory findByName(String name);
}
