package com.example.CafeTour.controller;

import com.example.CafeTour.Message;
import com.example.CafeTour.auth.CheckPasswordValidator;
import com.example.CafeTour.domain.User;
import com.example.CafeTour.domain.UserCreateForm;
import com.example.CafeTour.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PasswordChangeController {
    private final CheckPasswordValidator checkPasswordValidator;
    private final UserService userService;

    @InitBinder
    public void validatorBinder(WebDataBinder binder){
        binder.addValidators(checkPasswordValidator);
    }
/*
    @GetMapping("/user-password-update")
    public ModelAndView userInfo(ModelAndView mav, Principal principal) {
        User userDto = userService.findByEmail(principal.getName());
        UserCreateForm userCreateForm=UserCreateForm.builder()
                .username(userDto.getNickName())
                .email(userDto.getEmail())
                .password1(userDto.getPw())
                .password2(userDto.getPw())
                .build(); //회원정보수정창에 넘겨줄 현재 로그인한 유저의 정보
        mav.addObject("dto",userCreateForm);
        mav.addObject("userId",userDto.getId());
        mav.setViewName("/users/user_password_update_form");
        return mav;
    }*/

    @PostMapping("/user-password-update")
    public ModelAndView userInfoUpdate(@Valid UserCreateForm userCreateForm, Errors errors, Long userId, ModelAndView mav) {
        if (errors.hasErrors()) {
            mav.addObject("dto",userCreateForm);
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                mav.addObject(key, validatorResult.get(key));
            }
            /* 회원정보수정 페이지로 리턴 */
            mav.setViewName("/users/user_password_update_form");
            return mav;
        }
        userService.updatePassword(userCreateForm.getPassword1(),userId);

        mav.addObject("data", new Message("비밀번호 수정이 완료되었습니다.", "/user-info"));
        mav.setViewName("Message");
        return mav;
    }
}
