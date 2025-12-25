package com.northwollo.tourism.publicsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PublicTourismSearchResponseDto {

    private String name;
    private String imageUrl;
    private long viewersCount;
}
