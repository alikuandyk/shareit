package org.example.shareit.item;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.shareit.item.dto.ItemCreateDto;
import org.example.shareit.item.dto.ItemMapper;
import org.example.shareit.item.dto.ItemResponseDto;
import org.example.shareit.item.dto.ItemUpdateDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/items")
@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @PostMapping
    public ItemResponseDto create(
            @RequestHeader("X-Sharer-User-Id") int userId,
            @Valid @RequestBody ItemCreateDto itemCreateDto
            ) {
        Item item = itemMapper.fromCreate(itemCreateDto);
        return itemMapper.toResponse(itemService.create(item, userId));
    }

    @PatchMapping("/{id}")
    public ItemResponseDto update(
            @RequestHeader("X-Sharer-User-Id") int userId,
            @PathVariable int id,
            @Valid @RequestBody ItemUpdateDto itemUpdate
            ) {
        Item item = itemMapper.fromUpdate(itemUpdate);
        return itemMapper.toResponse(itemService.update(userId, item, id));
    }

    @GetMapping
    public List<ItemResponseDto> findAll(@RequestHeader("X-Sharer-User-Id") int userId) {
        List<ItemResponseDto> itemResponseDtos =
                itemService.findAll(userId).stream()
                        .map(itemMapper::toResponse)
                        .toList();

        return itemResponseDtos;
    }

    @GetMapping("/{itemId}")
    public ItemResponseDto findById(@PathVariable int itemId) {
        Item item = itemService.findById(itemId);
        return itemMapper.toResponse(item);
    }

    // TODO: переделать под респонсДТО
    @GetMapping("/search")
    public List<Item> findByText(@RequestParam String text) {
        return itemService.findByText(text);
    }

    @DeleteMapping("/{itemId}")
    public void deleteById(@PathVariable int itemId) {
        itemService.deleteById(itemId);
    }
}
