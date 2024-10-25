package org.example.shareit.item;

import lombok.RequiredArgsConstructor;
import org.example.shareit.exception.*;
import org.example.shareit.item.dto.ItemMapper;
import org.example.shareit.user.User;
import org.example.shareit.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemMapper itemMapper;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public Item create(Item item, int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("Пользователь с такой ID не найден");
        }

        item.setOwner(optionalUser.get());
        return itemRepository.create(item);
    }

    public Item update(int userId, Item updatedItem, int itemId) {
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (optionalItem.isEmpty()) {
            throw new UserAlreadyExistException("Товар с такой ID не найден");
        }

        userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Пользователь с такой ID не найден"));

        Item existingitem = itemRepository.findById(itemId).orElseThrow();
        User user = existingitem.getOwner();
        if (user.getId() != userId) {
            throw new ForbiddenException("ID пользователей не совпадает");
        }

        itemMapper.merge(existingitem, updatedItem);

        return itemRepository.update(existingitem, itemId);
    }

    public List<Item> findAll(int userId) {
        List<Item> items = itemRepository.findAll(userId);
        return items;
    }

    public Item findById(int itemId) {
        return itemRepository.findById(itemId).orElseThrow();
    }

    public List<Item> findByText(String text) {

        return itemRepository.findByText(text);
    }

    public void deleteById(int itemId) {
        itemRepository.deleteById(itemId);
    }

}
