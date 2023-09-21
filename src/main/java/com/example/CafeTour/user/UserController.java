package com.example.CafeTour.user;

import com.example.CafeTour.config.mail.Message;
import com.example.CafeTour.auth.CheckEmailValidator;
import com.example.CafeTour.auth.CheckNickNameValidator;
import com.example.CafeTour.auth.CheckPasswordValidator;
import com.example.CafeTour.user.userdto.UserRequestDto;
import com.example.CafeTour.user.userdto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @InitBinder
    public void validatorBinder(WebDataBinder binder){
        binder.addValidators(checkEmailValidator);
        binder.addValidators(checkNickNameValidator);
        binder.addValidators(checkPasswordValidator);
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
    public UserResponseDto userInfo(Principal principal) {
        return  userService.findByEmail(principal.getName());
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
    public UserResponseDto findByEmail(Principal principal) {
       return userService.findByEmail(principal.getName());
    }

    @GetMapping("/user-check")
    public UserResponseDto userCheck(Principal principal) {
       return userService.findByEmail(principal.getName());
    }

    @PostMapping("delete-user") //유저 탈퇴
    public boolean deleteUser(@RequestBody UserRequestDto requestDto, Principal principal) {
        return userService.deleteUser(requestDto.getPassword(), principal);
    }

    @GetMapping("/email-check") //이메일 중복 체크
    public ResponseEntity<Boolean> emailCheck(@RequestParam(name = "email")String email){
        return ResponseEntity.ok(userService.doubleCheckEmail(email));
    }

    @PostMapping("/password-find") //임시 비밀번호 발급
    public Long passwordFind(@RequestBody UserRequestDto requestDto) throws MessagingException {
        return userService.findPassword(requestDto.getEmail());
    }
}

