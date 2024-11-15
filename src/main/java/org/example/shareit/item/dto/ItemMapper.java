package org.example.shareit.item.dto;

import org.example.shareit.item.Comment;
import org.example.shareit.item.Item;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemMapper {
    public Item fromCreate(ItemCreateDto itemCreate) {
        Item item = new Item();
        item.setName(itemCreate.getName());
        item.setDescription(itemCreate.getDescription());
        item.setIsAvailable(itemCreate.getAvailable());
        return item;
    }

    public Item fromUpdate(ItemUpdateDto itemUpdate) {
        Item item = new Item();
        item.setName(itemUpdate.getName());
        item.setDescription(itemUpdate.getDescription());
        item.setIsAvailable(itemUpdate.getAvailable());
        return item;
    }

    public ItemResponseDto toResponse(Item item) {
        ItemResponseDto response = new ItemResponseDto();
        response.setId(item.getId());
        response.setName(item.getName());
        response.setDescription(item.getDescription());
        response.setAvailable(item.getIsAvailable());
        if (item.getComments() != null) {
            response.setComments(item.getComments().stream().map(this::toResponse).collect(Collectors.toList()));
        }
        return response;
    }

    public List<ItemResponseDto> toResponse(List<Item> items) {
        return items.stream()
                .map(this::toResponse)
                .toList();
    }

    public Comment fromCreate(CommentCreateDto createDto) {
        Comment comment = new Comment();
        comment.setText(createDto.getText());

        return comment;
    }

    public CommentResponseDto toResponse(Comment comment) {
        CommentResponseDto response = new CommentResponseDto();
        response.setId(comment.getId());
        response.setAuthorName(comment.getAuthor().getName());
        response.setText(comment.getText());
        response.setCreated(comment.getCreated());

        return response;
    }

    public void merge(Item existingItem, Item updatedItem) {
        if (updatedItem.getName() != null) {
            existingItem.setName(updatedItem.getName());
        }

        if (updatedItem.getDescription() != null) {
            existingItem.setDescription(updatedItem.getDescription());
        }

        if (updatedItem.getIsAvailable() != null) {
            existingItem.setIsAvailable(updatedItem.getIsAvailable());
        }
    }
}
