package com.example.CafeTour.controller;

import com.example.CafeTour.Message;
import com.example.CafeTour.auth.CheckEmailValidator;
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
public class EmailChangeController {
    private final CheckEmailValidator checkEmailValidator;
    private final UserService userService;

    @InitBinder
    public void validatorBinder(WebDataBinder binder){
        binder.addValidators(checkEmailValidator);
    }

    @GetMapping("/user-email-update") //로그인한 유저 이메일 불러오기
    public UserResponseDto userEmailInfo(Principal principal) {
        return userService.findByEmail(principal.getName());
    }

    @PostMapping("/user-email-update")
    public ModelAndView userEmailUpdate(@Valid UserCreateForm userCreateForm, Errors errors, Long userId, ModelAndView mav) {
        if (errors.hasErrors()) {
            mav.addObject("dto",userCreateForm);
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                mav.addObject(key, validatorResult.get(key));
            }
            /* 회원정보수정 페이지로 리턴 */
            mav.setViewName("/user_email_update_form");
            return mav;
        }
        userService.updateEmail(userCreateForm.getEmail(),userId);

        mav.addObject("data", new Message("이메일 수정이 완료되었습니다.", "/user-info"));
        mav.setViewName("Message");
        return mav;
    }
}
