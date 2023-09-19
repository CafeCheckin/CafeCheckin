package com.example.CafeTour.controller;

import com.example.CafeTour.Message;
import com.example.CafeTour.auth.CheckNickNameValidator;
import com.example.CafeTour.domain.User;
import com.example.CafeTour.domain.UserCreateForm;
import com.example.CafeTour.dto.UserResponseDto;
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
public class NicknameChangeController {
    private final CheckNickNameValidator checkNickNameValidator;
    private final UserService userService;

    @InitBinder
    public void validatorBinder(WebDataBinder binder){
        binder.addValidators(checkNickNameValidator);
    }

    @GetMapping("/user-nickname-update") //로그인 유저 닉네임 정보 불러오기
    public UserResponseDto userNicknameInfo(Principal principal) {
        return userService.findByEmail(principal.getName());
    }

    @PostMapping("/user-nickname-update")
    public ModelAndView userNicknameUpdate(@Valid UserCreateForm userCreateForm, Errors errors, Principal principal,  ModelAndView mav) {
        if (errors.hasErrors()) {
            mav.addObject("dto",userCreateForm);

            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                mav.addObject(key, validatorResult.get(key));
            }
            /* 회원가입 페이지로 리턴 */
            mav.setViewName("/users/user_nickname_update_form");
            return mav;
        }

        userService.updateNickName(userCreateForm.getUsername(),principal.getName());
        mav.addObject("data", new Message("닉네임 수정이 완료되었습니다.", "/user-info"));
        mav.setViewName("Message");
        return mav;
    }
}
