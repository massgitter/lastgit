package com.atlas.skull.Mapper;

import com.atlas.skull.Dto.ItemCategoryRequest;
import com.atlas.skull.Dto.ItemCategoryResponse;
import com.atlas.skull.Dto.ItemRequest;
import com.atlas.skull.Dto.ItemResponse;
import com.atlas.skull.SkullModel.Item;
import com.atlas.skull.SkullModel.ItemCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(target = "isActive", expression = "java(1)")
    @Mapping(target = "id", ignore = true)
    ItemCategory saveIntoItemCategory(ItemCategoryRequest itemCategoryRequest);

    @Mapping(target = "active", source = "itemCategory.isActive")
    ItemCategoryResponse getFromItemCategory(ItemCategory itemCategory);

    @Mapping(target = "itemCategory", source = "itemCategory")
    @Mapping(target = "vatInclusive", expression = "java(0)")
    @Mapping(target = "unit", expression = "java(null)")
    @Mapping(target = "total", ignore = true)
    @Mapping(target = "qty", ignore = true)
    @Mapping(target = "isPenalizable", ignore = true)
    @Mapping(target = "isActive", expression = "java(1)")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "itemRequest.name")
    Item saveIntoItem(ItemRequest itemRequest, ItemCategory itemCategory);

    @Mapping(target = "active", source = "item.isActive")
    ItemResponse getFromItem(Item item);
}
