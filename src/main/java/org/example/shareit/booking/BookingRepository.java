package org.example.shareit.booking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findAllByBookerId(int bookerId);
    List<Booking> findAllByItemOwnerId(int ownerId);
    List<Booking> findAllByItemIdAndBookerIdAndStartDateBeforeAndStatusIs(int itemId, int bookerId, LocalDateTime time, BookingStatus status);
}
