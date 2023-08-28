package com.example.CafeTour.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "user_wish")
@Getter
@Setter
public class UserWish {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "Id")
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name ="user_id")
        private User user;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name ="cafe_id")
        private CafeInformation cafeInformation;
}
