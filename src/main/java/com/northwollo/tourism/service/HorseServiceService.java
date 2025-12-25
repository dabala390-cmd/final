package com.northwollo.tourism.service;

import com.northwollo.tourism.dto.request.HorseServiceCreateDto;
import com.northwollo.tourism.dto.request.HorseServiceUpdateDto;
import com.northwollo.tourism.dto.response.HorseServiceSummaryDto;
import java.util.List;
public interface HorseServiceService {

    Long create(HorseServiceCreateDto dto);

    void update(Long id, HorseServiceUpdateDto dto);

    void delete(Long id);
    List<HorseServiceSummaryDto> getHorseServicesByTourismPlace(Long tourismPlaceId);
    List<HorseServiceSummaryDto> getHorseServicesByRoadInfo(Long roadInfoId);
}
