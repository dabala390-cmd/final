package com.northwollo.tourism.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hotel_images")
@Getter
@Setter
@NoArgsConstructor
public class HotelImage extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(nullable = false)
    private String imageUrl; // ✅ Make sure this matches your service mapping
}
