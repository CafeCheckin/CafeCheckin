package com.example.CafeTour.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.*;

@Getter
@Setter
@Builder
public class UserCreateForm {
    @NotBlank(message = "이메일은 필수항목입니다.")
    @Email
    private String email;

    @NotBlank(message = "닉네임은 필수 입력값입니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,10}$" , message = "닉네임은 특수문자를 포함하지 않은 2~10자리여야 합니다.")
    private String username;

    @NotBlank(message = "패스워드는 필수 입력 값입니다.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*\\W)(?=\\S+$).{6,300}",
            message = "비밀번호는 영문자와 숫자, 특수기호가 적어도 1개 이상 포함된 6자~12자의 비밀번호여야 합니다.")
    private String password1;

    @NotBlank(message = "비밀번호 확인은 필수항목입니다.")
    private String password2;

    @Builder
    public UserCreateForm(String email, String username, String password1, String password2) {
        this.email = email;
        this.username = username;
        this.password1 = password1;
        this.password2 = password2;
    }


}
