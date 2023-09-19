package com.example.CafeTour.auth;

import com.example.CafeTour.user.UserCreateForm;
import com.example.CafeTour.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@RequiredArgsConstructor
public class CheckEmailValidator extends AbstractValidator<UserCreateForm> {
    private final UserRepository userRepository;
    @Override
    protected void doValidate(UserCreateForm dto, Errors errors) {
        if(userRepository.existsByEmail(dto.getEmail())) {
            errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일 입니다.");
        }
    }
}
