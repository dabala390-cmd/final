package com.northwollo.tourism.repository;

import com.northwollo.tourism.entity.BookingMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingMessageRepository extends JpaRepository<BookingMessage, Long> {
    List<BookingMessage> findByBookingId(Long bookingId);
}
