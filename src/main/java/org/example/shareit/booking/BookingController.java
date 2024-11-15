package org.example.shareit.booking;

import lombok.RequiredArgsConstructor;
import org.example.shareit.booking.dto.BookingCreateDto;
import org.example.shareit.booking.dto.BookingMapper;
import org.example.shareit.booking.dto.BookingResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.shareit.utils.RequestConstants.USER_HEADER;


@RequestMapping("/bookings")
@RestController
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    @PostMapping
    public BookingResponseDto create(
            @RequestHeader(USER_HEADER) int userId,
            @RequestBody BookingCreateDto bookingCreate
    ) {
        return bookingMapper.toResponse(bookingService.create(userId, bookingMapper.fromCreate(bookingCreate)));
    }

    @PatchMapping("/{bookingId}")
    public BookingResponseDto update(
            @RequestHeader(USER_HEADER) int ownerId,
            @PathVariable int bookingId,
            @RequestParam boolean approved
    ) {
        return bookingMapper.toResponse(bookingService.update(bookingId, approved, ownerId));
    }

    @GetMapping("/{bookingId}")
    public BookingResponseDto findById(
            @RequestHeader(USER_HEADER) int userId,
            @PathVariable int bookingId
    ) {
        return bookingMapper.toResponse(bookingService.findById(bookingId));
    }

    @GetMapping("/owner")
    public List<BookingResponseDto> findAllByItemOwnerId(
            @RequestHeader(USER_HEADER) int ownerId
    ) {
        return bookingMapper.toResponse(bookingService.findAllByItemOwnerId(ownerId));
    }

    @GetMapping
    public List<BookingResponseDto> findAllByBooker(
            @RequestHeader(USER_HEADER) int userId
    ) {
        return bookingMapper.toResponse(bookingService.findAllByBooker(userId));
    }
}
