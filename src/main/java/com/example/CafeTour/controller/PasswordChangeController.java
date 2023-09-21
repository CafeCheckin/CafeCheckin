package com.example.CafeTour.controller;

import com.example.CafeTour.config.mail.Message;
import com.example.CafeTour.auth.CheckPasswordValidator;
import com.example.CafeTour.user.UserCreateForm;
import com.example.CafeTour.user.userdto.UserResponseDto;
import com.example.CafeTour.user.UserService;
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

    @GetMapping("/user-password-update") //로그인한 유저 비밀번호 불러오기
    public UserResponseDto userInfo(Principal principal) {
        return userService.findByEmail(principal.getName());
    }

    @PostMapping("/user-password-update")
    public ModelAndView userInfoUpdate(@Valid UserCreateForm userCreateForm, Errors errors, Principal principal, ModelAndView mav) {
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
        //userService.updatePassword(userCreateForm.getPassword1(),principal.getName());

        mav.addObject("data", new Message("비밀번호 수정이 완료되었습니다.", "/user-info"));
        mav.setViewName("Message");
        return mav;
    }
}
