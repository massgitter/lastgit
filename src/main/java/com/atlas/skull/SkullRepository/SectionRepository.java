package com.atlas.skull.SkullRepository;

import com.atlas.skull.SkullModel.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

    Optional<Section> findBySectionAndGrade_Id(String section, Long grade_id);
}
