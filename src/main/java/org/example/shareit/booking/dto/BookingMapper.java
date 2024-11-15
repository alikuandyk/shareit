package org.example.shareit.booking.dto;

import lombok.RequiredArgsConstructor;
import org.example.shareit.booking.Booking;
import org.example.shareit.item.Item;
import org.example.shareit.item.dto.ItemMapper;
import org.example.shareit.user.dto.UserMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookingMapper {
    private final ItemMapper itemMapper;
    private final UserMapper userMapper;

    public Booking fromCreate(BookingCreateDto bookingCreate) {
        Booking booking = new Booking();
        booking.setItem(new Item(bookingCreate.getItemId()));
        booking.setStartDate(bookingCreate.getStart());
        booking.setEndDate(bookingCreate.getEnd());
        return booking;
    }

    public BookingResponseDto toResponse(Booking booking) {
        BookingResponseDto response = new BookingResponseDto();
        response.setId(booking.getId());
        response.setStart(booking.getStartDate());
        response.setEnd(booking.getEndDate());
        response.setStatus(booking.getStatus());
        response.setBooker(userMapper.toResponse(booking.getBooker()));
        response.setItem(itemMapper.toResponse(booking.getItem()));

        return response;
    }

    public List<BookingResponseDto> toResponse(List<Booking> bookings) {
        return bookings.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
