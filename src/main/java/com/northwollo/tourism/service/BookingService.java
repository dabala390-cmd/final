package com.northwollo.tourism.service;

import com.northwollo.tourism.dto.request.BookingRequestDto;
import com.northwollo.tourism.entity.BookingMessage;

import java.util.List;

public interface BookingService {

    Long createBooking(BookingRequestDto dto);

    void proposeCost(Long bookingId, Double totalCost);

    void uploadPaymentReceipt(Long bookingId, String receiptUrl);

    void approveBooking(Long bookingId);

    void sendMessage(Long bookingId, String message);

    List<BookingMessage> getMessages(Long bookingId);
}
