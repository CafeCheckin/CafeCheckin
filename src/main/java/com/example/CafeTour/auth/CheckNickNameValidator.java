package com.example.CafeTour.auth;

import com.example.CafeTour.user.UserCreateForm;
import com.example.CafeTour.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@RequiredArgsConstructor
public class CheckNickNameValidator extends AbstractValidator<UserCreateForm> {
    private final UserRepository userRepository;

    @Override
    protected void doValidate(UserCreateForm dto, Errors errors) {
        if(userRepository.existsByNickName(dto.getUsername())) {
            errors.rejectValue("username", "닉네임 중복 오류", "이미 사용중인 닉네임 입니다.");
        }
    }
}
