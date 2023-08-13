package com.example.CafeTour.controller;

import com.example.CafeTour.domain.User;
import com.example.CafeTour.domain.UserCreateForm;
import com.example.CafeTour.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm) {
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }
        try {
            userService.create(userCreateForm.getUsername(),
                    userCreateForm.getEmail(), userCreateForm.getPassword1());

        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup_form";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }
        return "home";
    }

    @GetMapping("/userInfoUpdate")
    public String userInfo(Model model, Principal principal) {
        User userDto = userService.findByEmail(principal.getName());
        model.addAttribute("userinfo2", userDto);
        return "UserUpdateForm";
    }

    @PostMapping("/userInfoUpdate")
    public String userInfoUpdate(@RequestParam String username, String password1, String email) {
        userService.updateUser(username, password1, email);
        return "cafe";
    }

    @GetMapping("/userinfo")
    public String findByEmail(Model model, Principal principal) {
        User userDto = userService.findByEmail(principal.getName());
        model.addAttribute("userinfo2", userDto);
        return "UserInfo";
    }

    @GetMapping("usercheck")
    public String userCheck(Model model, Principal principal) {
        User userDto = userService.findByEmail(principal.getName());
        model.addAttribute("userinfo2", userDto);
        return "withdrawal";
    }

    @PostMapping("deleteuser")
    public String deleteUser(@RequestParam String password, Principal principal) {
        boolean result = userService.deleteUser(password, principal);
        if (result == true)
            return "redirect:/home";
        else return "/usercheck";
    }
}

