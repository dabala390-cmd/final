package com.northwollo.tourism.entity;

import com.northwollo.tourism.enums.RoadType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
@Entity
@Table(name = "road_infos")
@Data
public class RoadInfo extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "tourism_place_id")
    private TourismPlace tourismPlace;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoadType roadType;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Double distanceByCar;
    private Double distanceByFoot;
    private Double distanceByPlane;
    private Double distanceByHorse;
    private Double totalDistance;

    @OneToOne(mappedBy = "roadInfo", cascade = CascadeType.ALL)
    private HorseService horseService;
}
