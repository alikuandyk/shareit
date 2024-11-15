package org.example.shareit.item;

import lombok.RequiredArgsConstructor;
import org.example.shareit.booking.Booking;
import org.example.shareit.booking.BookingRepository;
import org.example.shareit.booking.BookingStatus;
import org.example.shareit.exception.*;
import org.example.shareit.item.dto.ItemMapper;
import org.example.shareit.user.User;
import org.example.shareit.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemMapper itemMapper;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final CommentRepository commentRepository;
    private final BookingRepository bookingRepository;

    public Item create(Item item, int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("Пользователь с такой ID не найден");
        }

        item.setOwner(optionalUser.get());
        return itemRepository.save(item);
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

        return itemRepository.save(existingitem);
    }

    public List<Item> findAll(int userId) {
        return itemRepository.findAllByOwner_Id(userId);
    }

    public Item findById(int itemId) {
        return itemRepository.findById(itemId).orElseThrow();
    }

    public List<Item> findByText(String text) {
        if (text.isBlank()) {
            return Collections.emptyList();
        }
        return itemRepository.findByText(text);
    }

    public void deleteById(int itemId) {
        itemRepository.deleteById(itemId);
    }

    public Comment create(int userId, int itemId, Comment comment) {
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (optionalItem.isEmpty()) {
            throw new NotFoundException("Товар с такой ID не найден");
        }

        List<Booking> bookings = bookingRepository.findAllByItemIdAndBookerIdAndStartDateBeforeAndStatusIs(itemId, userId, LocalDateTime.now(), BookingStatus.APPROVED);
        if (bookings.isEmpty()) {
            throw new BadRequestException("Комментария не создан, не найден бронь");
        }
        comment.setItem(optionalItem.orElseThrow());
        comment.setCreated(LocalDateTime.now());
        comment.setAuthor(userRepository.findById(userId).orElseThrow());

        return commentRepository.save(comment);
    }
}
