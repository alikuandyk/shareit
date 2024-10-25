package org.example.shareit.item;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {
    private final Map<Integer, Item> itemMap = new HashMap<>();
    private static int id = 1;

    @Override
    public Item create(Item item) {
        item.setId(id++);
        itemMap.put(item.getId(), item);
        return item;
    }

    @Override
    public Item update(Item updatedItem, int itemId) {
        itemMap.put(itemId, updatedItem);
        return updatedItem;
    }

    @Override
    public List<Item> findAll(int userId) {
        return itemMap.values().stream()
                .filter(item -> item.getOwner().getId() == userId)
                .toList();
    }

    @Override
    public Optional<Item> findById(int itemId) {
        return itemMap.values().stream()
                .filter(item -> item.getId() == itemId)
                .findFirst();
    }

    @Override
    public List<Item> findByText(String text) {
        if (StringUtils.isBlank(text)) {
            return Collections.emptyList();
        }

        return itemMap.values().stream()
                .filter(Item::getAvailable)
                .filter(item -> StringUtils.containsAnyIgnoreCase(item.getName(), text)
                        || StringUtils.containsAnyIgnoreCase(item.getDescription(), text))
                .toList();
    }

    @Override
    public void deleteById(int itemId) {
        itemMap.remove(itemId);
    }
}
