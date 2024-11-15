package org.example.shareit.booking.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingCreateDto {
    Integer itemId;

    @FutureOrPresent(message = "Дата начало бронирования не может быть в прошлом")
    LocalDateTime start;

    @Future
    LocalDateTime end;
}
