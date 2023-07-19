package com.example.CafeTour.controller;

import com.example.CafeTour.domain.User;
import com.example.CafeTour.dto.UserDto;
import com.example.CafeTour.repository.UserRepository;
import com.example.CafeTour.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    private final UserService userService;

    /* @Autowired
     private BCryptPasswordEncoder bCryptPasswordEncoder;
 */
    @RequestMapping("/kakao")
    public String home(){
        return "kakao";
    }

    @RequestMapping("/home")
    public String h1(){
        return "home";
    }

    @RequestMapping("/LoginForm")
    public String loginForm(){
        return "LoginForm";
    }

    @GetMapping("/JoinForm")
    public String joinForm(){
        return "JoinForm";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/join")
    public String join(@ModelAttribute UserDto userDto){
       /* String rawPassword=user.getPw();
        String encPassword=bCryptPasswordEncoder.encode(rawPassword);
        user.setPw(encPassword);
        userRepository.save(user);*/
        User user=new User();
        user.setPw(userDto.getPw());
        user.setEmail(userDto.getEmail());
        user.setNickName(userDto.getNickName());
        userService.save(user);
        return "home";
    }

}
