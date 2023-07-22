package com.example.CafeTour.domain;

import com.example.CafeTour.repository.UserRepository;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String value;

    UserRole(String value){
        this.value=value;
    }
}
