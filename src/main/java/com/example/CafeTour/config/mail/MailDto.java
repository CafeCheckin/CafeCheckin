package com.example.CafeTour.config.mail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MailDto {
        private String address;
        private String title;
        private String message;
}
