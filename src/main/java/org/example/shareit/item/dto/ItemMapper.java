package org.example.shareit.item.dto;

import org.example.shareit.item.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public Item fromCreate(ItemCreateDto itemCreate) {
        Item item = new Item();
        item.setName(itemCreate.getName());
        item.setDescription(itemCreate.getDescription());
        item.setAvailable(itemCreate.getAvailable());
        return item;
    }

    public Item fromUpdate(ItemUpdateDto itemUpdate) {
        Item item = new Item();
        item.setName(itemUpdate.getName());
        item.setDescription(itemUpdate.getDescription());
        item.setAvailable(itemUpdate.getAvailable());
        return item;
    }

    public ItemResponseDto toResponse(Item item) {
        ItemResponseDto response = new ItemResponseDto();
        response.setId(item.getId());
        response.setName(item.getName());
        response.setDescription(item.getDescription());
        response.setAvailable(item.getAvailable());
        return response;
    }

    public void merge(Item existingItem, Item updatedItem) {
        if (updatedItem.getName() != null) {
            existingItem.setName(updatedItem.getName());
        }

        if (updatedItem.getDescription() != null) {
            existingItem.setDescription(updatedItem.getDescription());
        }

        if (updatedItem.getAvailable() != null) {
            existingItem.setAvailable(updatedItem.getAvailable());
        }
    }
}
