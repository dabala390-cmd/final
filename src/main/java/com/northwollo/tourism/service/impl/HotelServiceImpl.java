package com.northwollo.tourism.service.impl;

import com.northwollo.tourism.dto.request.HotelCreateDto;
import com.northwollo.tourism.dto.request.HotelUpdateDto;
import com.northwollo.tourism.dto.response.HotelDetailInfoDto;
import com.northwollo.tourism.dto.response.HotelRatingResponseDto;
import com.northwollo.tourism.entity.Hotel;
import com.northwollo.tourism.entity.HotelImage;
import com.northwollo.tourism.entity.HotelRating;
import com.northwollo.tourism.entity.TourismPlace;
import com.northwollo.tourism.exception.ResourceNotFoundException;
import com.northwollo.tourism.repository.HotelRatingRepository;
import com.northwollo.tourism.repository.HotelRepository;
import com.northwollo.tourism.repository.TourismPlaceRepository;
import com.northwollo.tourism.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.northwollo.tourism.dto.response.HotelSummaryDto;  // ← ADD THIS

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final TourismPlaceRepository tourismRepository;
    private final HotelRatingRepository hotelRatingRepository;

    @Override
    public Long create(HotelCreateDto dto) {
        TourismPlace place = tourismRepository.findById(dto.getTourismPlaceId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tourism place not found with ID: " + dto.getTourismPlaceId()));

        Hotel hotel = new Hotel();
        hotel.setTourismPlace(place);
        hotel.setName(dto.getName());
        hotel.setStarRating(dto.getStarRating());
        hotel.setContactInfo(dto.getContactInfo());
        hotel.setPolicies(dto.getPolicies());

        hotelRepository.save(hotel);
        return hotel.getId();
    }

    @Override
    public void update(Long id, HotelUpdateDto dto) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + id));

        if (dto.getName() != null && !dto.getName().isBlank()) {
            hotel.setName(dto.getName());
        }
        hotel.setStarRating(dto.getStarRating());
        if (dto.getContactInfo() != null && !dto.getContactInfo().isBlank()) {
            hotel.setContactInfo(dto.getContactInfo());
        }
        hotel.setPolicies(dto.getPolicies());

        hotelRepository.save(hotel); // save changes
    }

    @Override
    public void delete(Long id) {
        if (!hotelRepository.existsById(id)) {
            throw new ResourceNotFoundException("Hotel not found with ID: " + id);
        }
        hotelRepository.deleteById(id);
    }

    @Override
    public Hotel detail(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public HotelDetailInfoDto getHotelDetailInfo(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + hotelId));

        List<HotelRating> ratings = hotelRatingRepository.findByHotelId(hotelId);

        HotelDetailInfoDto dto = new HotelDetailInfoDto();
        dto.setId(hotel.getId());
        dto.setName(hotel.getName());
        dto.setDescription(hotel.getDescription());
        dto.setStars(hotel.getStarRating());
        dto.setContactInfo(hotel.getContactInfo());
        dto.setPolicies(hotel.getPolicies());

        dto.setImages(hotel.getImages() != null ?
                hotel.getImages().stream().map(HotelImage::getImageUrl).toList() : List.of());


        dto.setRatings(ratings.stream()
                .map(HotelRatingResponseDto::fromEntity)
                .toList());

        dto.setAverageRating(
                ratings.isEmpty() ? 0.0 :
                        ratings.stream()
                                .mapToInt(HotelRating::getRating)
                                .average()
                                .orElse(0.0)
        );

        return dto;
    }

    // ✅ Implementation of missing method
    @Override
    @Transactional(readOnly = true)
    public List<HotelSummaryDto> getHotels(Long tourismPlaceId) {
        return hotelRepository.findByTourismPlaceId(tourismPlaceId)
                .stream()
                .limit(3)  // Only first 3 hotels
                .map(this::toHotelSummary)  // Map to minimal DTO
                .toList();
    }

    private HotelSummaryDto toHotelSummary(Hotel hotel) {
        HotelSummaryDto summary = new HotelSummaryDto();
        summary.setId(hotel.getId());
        summary.setName(hotel.getName());
        summary.setStars(hotel.getStarRating());

        // Get first image or null
        String firstImage = hotel.getImages() != null && !hotel.getImages().isEmpty()
                ? hotel.getImages().get(0).getImageUrl()
                : null;
        summary.setImageUrl(firstImage);

        return summary;
    }
}
