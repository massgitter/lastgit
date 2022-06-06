package com.atlas.skull.SkullRepository;

import com.atlas.skull.SkullModel.Item;
import com.atlas.skull.SkullModel.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByItemCategory_Id(Long itemCategory_id);
}
