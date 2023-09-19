package com.example.CafeTour.userwish;

import com.example.CafeTour.cafeinformation.CafeInformation;
import com.example.CafeTour.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
