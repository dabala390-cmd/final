package com.northwollo.tourism.controller;

import com.northwollo.tourism.dto.request.BookingRequestDto;
import com.northwollo.tourism.entity.BookingMessage;
import com.northwollo.tourism.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public Long create(@RequestBody BookingRequestDto dto) {
        return bookingService.createBooking(dto);
    }

    @PostMapping("/{id}/cost")
    @PreAuthorize("hasRole('HOTEL_OWNER')")
    public void proposeCost(@PathVariable Long id,
                            @RequestParam Double totalCost) {
        bookingService.proposeCost(id, totalCost);
    }

    @PostMapping("/{id}/receipt")
    @PreAuthorize("isAuthenticated()")
    public void uploadReceipt(@PathVariable Long id,
                              @RequestParam String receiptUrl) {
        bookingService.uploadPaymentReceipt(id, receiptUrl);
    }

    @PostMapping("/{id}/approve")
    @PreAuthorize("hasRole('HOTEL_OWNER')")
    public void approve(@PathVariable Long id) {
        bookingService.approveBooking(id);
    }

    @PostMapping("/{id}/chat")
    @PreAuthorize("isAuthenticated()")
    public void sendMessage(@PathVariable Long id,
                            @RequestParam String message) {
        bookingService.sendMessage(id, message);
    }

    @GetMapping("/{id}/chat")
    @PreAuthorize("isAuthenticated()")
    public List<BookingMessage> getMessages(@PathVariable Long id) {
        return bookingService.getMessages(id);
    }
}
