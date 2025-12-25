package com.northwollo.tourism.dto.request;

import com.northwollo.tourism.enums.RoadType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoadInfoCreateDto {

    @NotNull(message = "Tourism place ID is required")
    private Long tourismPlaceId;

    @NotNull(message = "Road type is required")
    private RoadType roadType;

    private String description;

    @Positive(message = "Distance by car must be positive")
    private Double distanceByCar;

    @Positive(message = "Distance by foot must be positive")
    private Double distanceByFoot;

    @Positive(message = "Distance by plane must be positive")
    private Double distanceByPlane;

    @Positive(message = "Distance by horse must be positive")
    private Double distanceByHorse;

    @Positive(message = "Total distance must be positive")
    private Double totalDistance;
}
