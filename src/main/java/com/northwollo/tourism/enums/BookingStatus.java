package com.northwollo.tourism.enums;

public enum BookingStatus {
    PENDING,        // Booking created, awaiting hotel cost proposal
    COST_PROPOSED,  // Hotel has proposed a total cost
    PAID,           // Client has uploaded bank receipt
    APPROVED,       // Hotel has approved the booking
    CANCELLED       // Booking cancelled
}
