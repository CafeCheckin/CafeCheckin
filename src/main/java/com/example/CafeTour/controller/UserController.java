package com.example.CafeTour.controller;

import com.example.CafeTour.domain.User;
import com.example.CafeTour.domain.UserCreateForm;
import com.example.CafeTour.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    public ModelAndView signup(ModelAndView mav) {
        mav.setViewName("signup_form");
        return mav;
    }

    @PostMapping("/signup")
    public ModelAndView signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult, ModelAndView mav) {
        if (bindingResult.hasErrors()) {
            mav.setViewName("signup_form");
            return mav;
        }

        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            mav.setViewName("signup_form");
            return mav;
        }
        try {
            userService.create(userCreateForm.getUsername(),
                    userCreateForm.getEmail(), userCreateForm.getPassword1());

        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            mav.setViewName("signup_form");
            return mav;
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            mav.setViewName("signup_form");
            return mav;
        }
        mav.setViewName("home");
        return mav;
    }

    @GetMapping("/user-info-update")
    public ModelAndView userInfo(ModelAndView mav, Principal principal) {
        User userDto = userService.findByEmail(principal.getName());
        mav.addObject("userinfo2", userDto);
        mav.setViewName("user_update_form");
        return mav;
    }

    @PostMapping("/user-info-update")
    public ModelAndView userInfoUpdate(@RequestParam String username, String password1, String email, ModelAndView mav) {
        userService.updateUser(username, password1, email);
        mav.setViewName("cafe");
        return mav;
    }

    @GetMapping("/user-info")
    public ModelAndView findByEmail(ModelAndView mav, Principal principal) {
        User userDto = userService.findByEmail(principal.getName());
        mav.addObject("userinfo2", userDto);
        mav.setViewName("user_info");
        return mav;
    }

    @GetMapping("user-check")
    public ModelAndView userCheck(ModelAndView mav, Principal principal) {
        User userDto = userService.findByEmail(principal.getName());
        mav.addObject("userinfo2", userDto);
        mav.setViewName("user_withdrawal");
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
}

