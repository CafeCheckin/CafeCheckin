package com.example.CafeTour.controller;

import com.example.CafeTour.Message;
import com.example.CafeTour.auth.CheckEmailValidator;
import com.example.CafeTour.auth.CheckNickNameValidator;
import com.example.CafeTour.auth.CheckPasswordValidator;
import com.example.CafeTour.domain.User;
import com.example.CafeTour.domain.UserCreateForm;
import com.example.CafeTour.dto.MailDto;
import com.example.CafeTour.service.SendMailService;
import com.example.CafeTour.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.mail.MessagingException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final CheckEmailValidator checkEmailValidator;
    private final CheckNickNameValidator checkNickNameValidator;
    private final CheckPasswordValidator checkPasswordValidator;
    private final SendMailService sendMailService;
    private final PasswordEncoder encoder;
    @InitBinder
    public void validatorBinder(WebDataBinder binder){
        binder.addValidators(checkEmailValidator);
        binder.addValidators(checkNickNameValidator);
        binder.addValidators(checkPasswordValidator);
    }

    @GetMapping("/signup")
    public ModelAndView signup(ModelAndView mav) {
        mav.addObject("dto",new UserCreateForm());
        mav.setViewName("/users/signup_form");
        return mav;
    }

    @PostMapping("/signup")
    public ModelAndView signup(@Valid UserCreateForm userCreateForm, Errors errors, ModelAndView mav) {
        if (errors.hasErrors()) {
            mav.addObject("dto",userCreateForm);

            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                mav.addObject(key, validatorResult.get(key));
            }
            /* 회원가입 페이지로 리턴 */
            mav.setViewName("/users/signup_form");
            return mav;
        }
        userService.create(userCreateForm.getUsername(),
                userCreateForm.getEmail(), userCreateForm.getPassword1());
        mav.addObject("data", new Message("회원가입이 완료되었습니다.", "/home"));
        mav.setViewName("Message");
        return mav;
    }

    @GetMapping("/user-info-update")
    public ModelAndView userInfo(ModelAndView mav, Principal principal) {
        User userDto = userService.findByEmail(principal.getName());
        UserCreateForm userCreateForm=new UserCreateForm(); //회원정보수정창에 넘겨줄 현재 로그인한 유저의 정보
        userCreateForm.setEmail(userDto.getEmail());
        userCreateForm.setUsername(userDto.getNickName());
        mav.addObject("dto",userCreateForm);
        mav.addObject("userId",userDto.getId());
        mav.setViewName("/users/user_update_form");
        return mav;
    }
    @PostMapping("/user-info-update")
    public ModelAndView userInfoUpdate(@Valid UserCreateForm userCreateForm, Errors errors, ModelAndView mav) {
        if (errors.hasErrors()) {
            mav.addObject("dto",userCreateForm);
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                mav.addObject(key, validatorResult.get(key));
            }
            //회원정보수정 페이지로 리턴
            mav.setViewName("/users/user_update_form");
            return mav;
        }
        mav.addObject("data", new Message("회원정보 수정이 완료되었습니다.", "/cafe"));
        mav.setViewName("Message");
        return mav;
    }

    @GetMapping("/user-info")
    public ModelAndView findByEmail(ModelAndView mav, Principal principal) {
        User userDto = userService.findByEmail(principal.getName());
        mav.addObject("userinfo2", userDto);
        mav.setViewName("/users/user_info");
        return mav;
    }

    @GetMapping("user-check")
    public ModelAndView userCheck(ModelAndView mav, Principal principal) {
        User userDto = userService.findByEmail(principal.getName());
        mav.addObject("userinfo2", userDto);
        mav.setViewName("/users/user_withdrawal");
        return mav;
    }

    @PostMapping("delete-user")
    public ModelAndView deleteUser(@RequestParam String password, Principal principal, ModelAndView mav) {
        boolean result = userService.deleteUser(password, principal);
        if (result == true) {
            mav.setViewName("redirect:/home");
            return mav;
        } else {
            mav.setViewName("/user-check");
            return mav;
        }
    }

    @GetMapping("/email-check")
    public ResponseEntity<Boolean> emailCheck(@RequestParam(name = "email")String email){
        return ResponseEntity.ok(userService.doubleCheckEmail(email));
    }

    @GetMapping("/find-password")
    public ModelAndView findPassword(ModelAndView mav){
        mav.setViewName("/users/user_find_password");
        return mav;
    }

    @PostMapping("password-find")
    public ModelAndView passwordFind(User user,ModelAndView mav) throws MessagingException {
        System.out.println("받아온 이메일"+user.getEmail());

        if(userService.findByEmail(user.getEmail())!=null){
            User userdto=userService.findByEmail(user.getEmail());
            MailDto dto = sendMailService.createMailAndChangePassword(userdto.getEmail(), userdto.getNickName());
            sendMailService.mailSend(dto);
            mav.addObject("data", new Message("입력하신 이메일로 임시 비밀번호가 전송되었습니다", "/home"));
            mav.setViewName("Message");
        }
        else{
            mav.addObject("data", new Message("존재하지 않은 이메일입니다!", "/find-password"));
            mav.setViewName("Message");
            return mav;
        }
        return mav;
    }
}

