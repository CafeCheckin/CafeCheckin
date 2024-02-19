package com.example.CafeTour.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cafe_image")
@Getter
@Setter
public class CafeImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="cafe_id")
    private CafeInformation cafeInformation;

    @Column(name = "cafe_image_url")
    private String cafeImageUrl;
}
