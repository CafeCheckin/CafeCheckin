package com.example.CafeTour.controller;
import com.example.CafeTour.domain.User;
import com.example.CafeTour.dto.UserDto;
import com.example.CafeTour.repository.UserRepository;
import com.example.CafeTour.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserService userService;

    @RequestMapping("/home")
    public String h1(){
        return "home";
    }

    @RequestMapping("/lll")
    public String pra1(){
        return "lll";
    }

    @RequestMapping("/cafe")
    public String LoginHome(Principal principal,Model model){
        System.out.println(principal.getName());
        model.addAttribute("userinfo1",principal.getName());
        return "cafe";
    }

   /* @GetMapping("/userInfoUpdate")
    public String userInfo(Model model,Principal principal){
        User userDto=userService.findByEmail(principal.getName());
        model.addAttribute("userinfo2",userDto);
        return "UserUpdateForm";
    }*/

   /* @PostMapping("/userInfoUpdate")
    public String userInfoUpdate(@ModelAttribute UserDto userDto){
        userService.updateUser(userDto);
        return "cafe";
    }*/
}
