package com.example.CafeTour.cafeinformation;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cafe_information")
@Getter
public class CafeInformation {
        @javax.persistence.Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "Id")
        private Long id;

        @Column(name = "cafe_name")
        private String cafeName;

        @Column(name = "open_at")
        private String openAt;

        @Column(name = "close_at")
        private String closeAt;

        @Column(name = "tel_num")
        private String telNum;

        @Column(name="cafe_mood")
        private String cafeMood;

        @Column(name="address")
        private String address;

        @Column(name="latitude")
        private String latitude;

        @Column(name="longitude")
        private String longitude;

        @Column(name = "cafe_grade", columnDefinition = "double default 0.0",nullable = false)
        private Double cafeGrade;

        public void updateCafeGrade(double cafeGrade){
                this.cafeGrade=cafeGrade;
        }
}
