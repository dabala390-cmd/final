package com.northwollo.tourism.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorseServiceSummaryDto {
    private Long id;
    private String ownerName;
    private String contactInfo;
    private Double cost;
}
