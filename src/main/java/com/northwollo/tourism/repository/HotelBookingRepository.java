package com.northwollo.tourism.repository;

import com.northwollo.tourism.entity.HotelBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelBookingRepository extends JpaRepository<HotelBooking, Long> {
    // add custom queries if needed
}
