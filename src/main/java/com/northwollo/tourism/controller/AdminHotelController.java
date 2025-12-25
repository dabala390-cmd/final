package com.northwollo.tourism.controller;

import com.northwollo.tourism.dto.request.HotelCreateDto;
import com.northwollo.tourism.dto.request.HotelUpdateDto;
import com.northwollo.tourism.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/hotels")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminHotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<Long> create(@Valid @RequestBody HotelCreateDto dto) {
        return ResponseEntity.ok(hotelService.create(dto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @Valid @RequestBody HotelUpdateDto dto) {
        hotelService.update(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        hotelService.delete(id);
        return ResponseEntity.ok().build();
    }
}
