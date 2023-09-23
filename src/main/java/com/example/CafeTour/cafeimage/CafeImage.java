package com.example.CafeTour.cafeimage;

import com.example.CafeTour.cafeinformation.CafeInformation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    @JsonIgnore
    private CafeInformation cafeInformation;

    @Column(name = "cafe_image_url")
    private String cafeImageUrl;
}
