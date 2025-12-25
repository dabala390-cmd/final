package com.northwollo.tourism.service;

import com.northwollo.tourism.publicsearch.dto.PublicTourismSearchRequestDto;
import com.northwollo.tourism.publicsearch.dto.PublicTourismSearchResponseDto;

import java.util.List;

public interface PublicTourismSearchService {

    List<PublicTourismSearchResponseDto> search(PublicTourismSearchRequestDto request);
}
