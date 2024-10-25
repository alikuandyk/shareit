package org.example.shareit.item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    Item create(Item item);
    Item update(Item updatedItem, int itemId);
    List<Item> findAll(int userId);
    Optional<Item> findById(int itemId);
    List<Item> findByText(String text);
    void deleteById(int itemId);
}
