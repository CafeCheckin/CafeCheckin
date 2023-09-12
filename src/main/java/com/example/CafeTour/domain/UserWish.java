package com.example.CafeTour.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "user_wish")
@Getter
@Setter
@NoArgsConstructor
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

        @Builder
        public UserWish(Long id, User user, CafeInformation cafeInformation) {
                this.id = id;
                this.user = user;
                this.cafeInformation = cafeInformation;
        }
}
