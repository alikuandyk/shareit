package org.example.shareit.item.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.shareit.booking.dto.BookingResponseDto;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class ItemResponseDto {
    long id;
    String name;
    String description;
    Boolean available;
    BookingResponseDto lastBooking;
    BookingResponseDto nextBooking;
    List<CommentResponseDto> comments;
}
