package com.atlas.skull.Controller;

import com.atlas.skull.Dto.ItemCategoryRequest;
import com.atlas.skull.Dto.ItemCategoryResponse;
import com.atlas.skull.Dto.ItemRequest;
import com.atlas.skull.Dto.ItemResponse;
import com.atlas.skull.Service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController

public class ItemController {

    private final ItemService itemService;

    @PostMapping("/itemCategory")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "create a category to register many other items in it. consider it as a box")
    public String saveItemCategory(@RequestBody ItemCategoryRequest itemCategoryRequest){

        itemService.addItemCateogry(itemCategoryRequest);
        return "success";
    }

    @PutMapping("/itemCategory")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "edit item category")
    public String updateItemCategory(@RequestBody ItemCategoryRequest itemCategoryRequest, Long id){

        itemService.editItemCategory(itemCategoryRequest, id);
        return "update success";
    }

    @PostMapping("/itemCategory/deactivate")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "deactivate item category")
    public String deactivateItemCategory(Long id){

        itemService.deactivateItemCategory(id);
        return "deactivated";
    }

    @GetMapping("/itemCategory")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER')")
    @Operation(summary = "get all item categories")
    public List<ItemCategoryResponse> returnAllItemCategories(){

        return itemService.getAllItemCategory();
    }

    @PostMapping("/item")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "insert an item into an item category")
    public String saveItem(@RequestBody ItemRequest itemRequest){

        itemService.addItem(itemRequest);
        return "success";
    }

    @PutMapping("/item")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "edit item")
    public String updateItem(@RequestBody ItemRequest itemRequest, Long id){

        itemService.editItem(itemRequest, id);
        return "update success";
    }

    @PostMapping("item/deativate")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "deactivate item")
    public String deactivateItem(Long id){

        itemService.deactivateItem(id);
        return "deactivated";
    }

    @GetMapping("/item")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER')")
    @Operation(summary = "get all items")
    public List<ItemResponse> getAllItems(Long itemCategoryId){

        return itemService.getAllItemsByCategory(itemCategoryId);
    }
}
