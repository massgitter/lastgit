package com.atlas.skull.SkullRepository;

import com.atlas.skull.SkullModel.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {

    ItemCategory findByName(String name);
}