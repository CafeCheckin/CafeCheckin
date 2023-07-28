package com.example.CafeTour.domain;
import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "cafe_information")
@Data
public class Boarding {
        @javax.persistence.Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "Id")
        private Long Id;

        @Column(name = "cafe_name")
        private String cafeName;

        @Column(name = "open_at")
        private String openAt;

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
}
