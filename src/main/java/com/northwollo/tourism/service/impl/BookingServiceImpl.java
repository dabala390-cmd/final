package com.northwollo.tourism.service.impl;

import com.northwollo.tourism.dto.request.BookingRequestDto;
import com.northwollo.tourism.entity.BookingMessage;
import com.northwollo.tourism.entity.HotelBooking;
import com.northwollo.tourism.entity.Hotel;
import com.northwollo.tourism.enums.BookingStatus;
import com.northwollo.tourism.exception.ResourceNotFoundException;
import com.northwollo.tourism.repository.HotelBookingRepository;
import com.northwollo.tourism.repository.BookingMessageRepository;
import com.northwollo.tourism.repository.HotelRepository;
import com.northwollo.tourism.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class BookingServiceImpl implements BookingService {

    private final HotelBookingRepository bookingRepository; // manages HotelBooking
    private final HotelRepository hotelRepository;
    private final BookingMessageRepository messageRepository; // manages BookingMessage

    @Override
    public Long createBooking(BookingRequestDto dto) {
        Hotel hotel = hotelRepository.findById(dto.getHotelId())
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found: " + dto.getHotelId()));

        HotelBooking booking = new HotelBooking();
        booking.setHotel(hotel);
        booking.setCheckInDate(dto.getCheckInDate());
        booking.setCheckOutDate(dto.getCheckOutDate());
        booking.setStatus(BookingStatus.PENDING);

        bookingRepository.save(booking); // ✅ now correct
        return booking.getId();
    }

    @Override
    public void proposeCost(Long bookingId, Double totalCost) {
        HotelBooking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found: " + bookingId));
        booking.setProposedCost(totalCost);
        bookingRepository.save(booking);
    }

    @Override
    public void uploadPaymentReceipt(Long bookingId, String receiptUrl) {
        HotelBooking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found: " + bookingId));
        booking.setPaymentReceiptUrl(receiptUrl);
        bookingRepository.save(booking);
    }

    @Override
    public void approveBooking(Long bookingId) {
        HotelBooking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found: " + bookingId));
        booking.setStatus(BookingStatus.APPROVED);
        bookingRepository.save(booking);
    }

    @Override
    public void sendMessage(Long bookingId, String message) {
        HotelBooking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found: " + bookingId));

        BookingMessage msg = new BookingMessage();
        msg.setBooking(booking);
        msg.setMessage(message);

        messageRepository.save(msg); // ✅ correct now
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingMessage> getMessages(Long bookingId) {
        HotelBooking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found: " + bookingId));

        return messageRepository.findByBookingId(bookingId); // ✅ correct now
    }
}
