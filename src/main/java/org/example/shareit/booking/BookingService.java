package org.example.shareit.booking;

import lombok.RequiredArgsConstructor;
import org.example.shareit.booking.dto.BookingMapper;
import org.example.shareit.exception.BadRequestException;
import org.example.shareit.exception.ForbiddenException;
import org.example.shareit.exception.NotFoundException;
import org.example.shareit.item.Item;
import org.example.shareit.item.ItemRepository;
import org.example.shareit.user.User;
import org.example.shareit.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public Booking create(int userId, Booking booking) {
        User booker = userRepository.findById(userId).orElseThrow();
        Item item = itemRepository.findById(booking.getItem().getId()).orElseThrow(() -> new NotFoundException("Предмет не найден"));

        if (item.getOwner() == booker) {
            throw new ForbiddenException("Запрещается бронировать свой предмет");
        }
        if (!item.getIsAvailable()) {
            throw new BadRequestException("Предмет должен быть доступным");
        }

        booking.setBooker(booker);
        booking.setItem(item);
        booking.setStatus(BookingStatus.WAITING);

        return bookingRepository.save(booking);
    }

    public Booking update(int bookingId, boolean status, int ownerId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        User owner = userRepository.findById(ownerId).orElseThrow();
        if (!booking.getItem().getOwner().equals(owner)) {
            throw new ForbiddenException("Этот предмет не ваш");
        }

        booking.setStatus(status ? BookingStatus.APPROVED : BookingStatus.REJECTED);
        return bookingRepository.save(booking);
    }

    public Booking findById(int bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow();
    }

    public List<Booking> findAllByItemOwnerId(int ownerId) {
        findById(ownerId);
        return bookingRepository.findAllByItemOwnerId(ownerId);
    }

    public List<Booking> findAllByBooker(int bookerId) {
        return bookingRepository.findAllByBookerId(bookerId);
    }

}
