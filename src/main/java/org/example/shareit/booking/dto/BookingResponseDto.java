package org.example.shareit.booking.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.shareit.booking.BookingStatus;
import org.example.shareit.item.dto.ItemResponseDto;
import org.example.shareit.user.dto.UserResponseDto;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingResponseDto {
    Integer id;
    LocalDateTime start;
    LocalDateTime end;
    BookingStatus status;
    UserResponseDto booker;
    ItemResponseDto item;
}
