package com.northwollo.tourism.controller;

import com.northwollo.tourism.entity.Hotel;
import com.northwollo.tourism.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/hotels")
@RequiredArgsConstructor
public class UserHotelController {

    private final HotelService hotelService;

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> detail(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.detail(id));
    }
}
