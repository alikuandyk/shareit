package org.example.shareit.item;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.shareit.item.dto.ItemCreateDto;
import org.example.shareit.item.dto.ItemMapper;
import org.example.shareit.item.dto.ItemResponseDto;
import org.example.shareit.item.dto.ItemUpdateDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.shareit.utils.RequestConstants.USER_HEADER;

@RequestMapping("/items")
@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @PostMapping
    public ItemResponseDto create(
            @RequestHeader(USER_HEADER) int userId,
            @Valid @RequestBody ItemCreateDto itemCreateDto
            ) {
        Item item = itemMapper.fromCreate(itemCreateDto);
        return itemMapper.toResponse(itemService.create(item, userId));
    }

    @PatchMapping("/{id}")
    public ItemResponseDto update(
            @RequestHeader(USER_HEADER) int userId,
            @PathVariable int id,
            @Valid @RequestBody ItemUpdateDto itemUpdate
            ) {
        Item item = itemMapper.fromUpdate(itemUpdate);
        return itemMapper.toResponse(itemService.update(userId, item, id));
    }

    @GetMapping
    public List<ItemResponseDto> findAll(@RequestHeader(USER_HEADER) int userId) {
        return itemMapper.toResponse(itemService.findAll(userId));
    }

    @GetMapping("/{itemId}")
    public ItemResponseDto findById(@PathVariable int itemId) {
        Item item = itemService.findById(itemId);
        return itemMapper.toResponse(item);
    }

    @GetMapping("/search")
    public List<ItemResponseDto> findByText(@RequestParam String text) {
        return itemMapper.toResponse(itemService.findByText(text));
    }

    @DeleteMapping("/{itemId}")
    public void deleteById(@PathVariable int itemId) {
        itemService.deleteById(itemId);
    }
}
