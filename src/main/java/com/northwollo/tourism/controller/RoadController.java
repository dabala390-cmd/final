package com.northwollo.tourism.controller;

import com.northwollo.tourism.dto.request.RoadInfoCreateDto;
import com.northwollo.tourism.dto.request.RoadInfoUpdateDto;
import com.northwollo.tourism.dto.response.RoadInfoDto;
import com.northwollo.tourism.service.RoadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/roads")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class RoadController {

    private final RoadService roadService;

    @PostMapping
    public ResponseEntity<Long> create(@Valid @RequestBody RoadInfoCreateDto dto) {
        return ResponseEntity.ok(roadService.createRoad(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody RoadInfoUpdateDto dto) {
        roadService.updateRoad(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roadService.deleteRoad(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tourism/{tourismPlaceId}")
    public ResponseEntity<List<RoadInfoDto>> getByTourismPlace(@PathVariable Long tourismPlaceId) {
        return ResponseEntity.ok(roadService.getRoadsByTourismPlace(tourismPlaceId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoadInfoDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(roadService.getRoadById(id));
    }
}
