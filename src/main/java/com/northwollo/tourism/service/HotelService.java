package com.northwollo.tourism.service;

import com.northwollo.tourism.dto.request.HotelCreateDto;
import com.northwollo.tourism.dto.request.HotelUpdateDto;
import com.northwollo.tourism.dto.response.HotelDetailInfoDto;
import com.northwollo.tourism.dto.response.HotelSummaryDto;
import com.northwollo.tourism.entity.Hotel;

import java.util.List;

public interface HotelService {
    Long create(HotelCreateDto dto);
    void update(Long id, HotelUpdateDto dto);
    void delete(Long id);
    Hotel detail(Long id);
    HotelDetailInfoDto getHotelDetailInfo(Long hotelId);
    List<HotelSummaryDto> getHotels(Long tourismPlaceId);

}
