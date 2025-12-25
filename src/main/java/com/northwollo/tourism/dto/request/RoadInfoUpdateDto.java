package com.northwollo.tourism.dto.request;

import com.northwollo.tourism.enums.RoadType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoadInfoUpdateDto {

    private RoadType roadType;
    private String description;
    private Double distanceByCar;
    private Double distanceByFoot;
    private Double distanceByPlane;
    private Double distanceByHorse;
    private Double totalDistance;
}
