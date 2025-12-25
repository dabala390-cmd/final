package com.northwollo.tourism.dto.response;

import lombok.Data;

@Data
public class HotelBookingResponseDto {
    private Long bookingId;
    private String hotelName;
    private String status;
}
