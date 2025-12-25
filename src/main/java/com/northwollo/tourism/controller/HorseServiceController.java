package com.northwollo.tourism.controller;

import com.northwollo.tourism.dto.request.HorseServiceCreateDto;
import com.northwollo.tourism.dto.request.HorseServiceUpdateDto;
import com.northwollo.tourism.service.HorseServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/horse-services")
@RequiredArgsConstructor
public class HorseServiceController {

    private final HorseServiceService horseServiceService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> create(
            @Valid @RequestBody HorseServiceCreateDto dto) {
        return ResponseEntity.ok(horseServiceService.create(dto));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> update(
            @PathVariable Long id,
            @Valid @RequestBody HorseServiceUpdateDto dto) {

        horseServiceService.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        horseServiceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
