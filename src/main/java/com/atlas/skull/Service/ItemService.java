package com.atlas.skull.Service;

import com.atlas.skull.Dto.ItemCategoryRequest;
import com.atlas.skull.Dto.ItemCategoryResponse;
import com.atlas.skull.Dto.ItemRequest;
import com.atlas.skull.Dto.ItemResponse;
import com.atlas.skull.Exception.ResourceNotFound;
import com.atlas.skull.Mapper.ItemMapper;
import com.atlas.skull.SkullModel.Item;
import com.atlas.skull.SkullModel.ItemCategory;
import com.atlas.skull.SkullRepository.ItemCategoryRepository;
import com.atlas.skull.SkullRepository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service

public class ItemService {

    private final ItemCategoryRepository itemCategoryRepository;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public void addItemCateogry(ItemCategoryRequest itemCategoryRequest){

        itemCategoryRepository.save(itemMapper.saveIntoItemCategory(itemCategoryRequest));
    }

    public void editItemCategory(ItemCategoryRequest itemCategoryRequest, Long id){

        ItemCategory category2bUpdated = itemCategoryRepository.findById(id).orElseThrow(()-> new
                ResourceNotFound(""));
        category2bUpdated.setName(itemCategoryRequest.getName());
        category2bUpdated.setIsActive(1);
        itemCategoryRepository.save(category2bUpdated);
    }

    public void deactivateItemCategory(Long id){

        ItemCategory category2bDeactivated = itemCategoryRepository.findById(id).orElseThrow(()-> new
                ResourceNotFound(""));
        category2bDeactivated.setIsActive(0);
        itemCategoryRepository.save(category2bDeactivated);

    }

    public List<ItemCategoryResponse> getAllItemCategory(){

        return itemCategoryRepository.findAll().stream().map(itemMapper::getFromItemCategory)
                .collect(Collectors.toList());
    }

    public void addItem(ItemRequest itemRequest){

        ItemCategory itemCategory = itemCategoryRepository.findById(itemRequest.getItemCategoryId()).orElseThrow(()->
                new ResourceNotFound(""));
        itemRepository.save(itemMapper.saveIntoItem(itemRequest, itemCategory));
    }

    public void editItem(ItemRequest itemRequest, Long id){

        Item item2bUpdated = itemRepository.findById(id).orElseThrow(()-> new ResourceNotFound(""));

        ItemCategory itemCategory = itemCategoryRepository.findById(itemRequest.getItemCategoryId()).orElseThrow(()->
                new ResourceNotFound(""));
        item2bUpdated.setName(itemRequest.getName());
        item2bUpdated.setUnitPrice(itemRequest.getUnitPrice());
        item2bUpdated.setIsActive(1);
        item2bUpdated.setItemCategory(itemCategory);
        itemRepository.save(item2bUpdated);
    }

    public void deactivateItem(Long id){

        Item item2bDeactivated = itemRepository.findById(id).orElseThrow(()-> new ResourceNotFound(""));
        item2bDeactivated.setIsActive(0);
        itemRepository.save(item2bDeactivated);
    }

    public List<ItemResponse> getAllItemsByCategory(Long itemCategoryId){

        return itemRepository.findByItemCategory_Id(itemCategoryId).stream()
                .map(itemMapper::getFromItem).collect(Collectors.toList());
    }
}
