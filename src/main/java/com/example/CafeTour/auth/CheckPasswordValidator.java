package com.example.CafeTour.auth;

import com.example.CafeTour.domain.UserCreateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@RequiredArgsConstructor
public class CheckPasswordValidator extends AbstractValidator<UserCreateForm> {
    @Override
    protected void doValidate(UserCreateForm dto, Errors errors) {
        if(!dto.getPassword1().equals(dto.getPassword2())) {
            errors.rejectValue("password2", "비밀번호 일치 오류", "비밀번호가 일치하지 않습니다.");
        }
    }
}
