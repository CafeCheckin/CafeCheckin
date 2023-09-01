package com.example.CafeTour.service;

import com.example.CafeTour.domain.User;
import com.example.CafeTour.dto.MailDto;
import com.example.CafeTour.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SendMailService {
    private final UserRepository userRepository;
    private static final String FROM_ADDRESS = "이메일주소";
    private final JavaMailSender javaMailSender;
    private final PasswordEncoder encoder;
    @Transactional
    public MailDto createMailAndChangePassword(String userEmail, String nickName){
        String tempPassword = getTempPassword();
        MailDto dto = new MailDto();
        dto.setAddress(userEmail);
        dto.setTitle(nickName+"님의 HOTTHINK 임시비밀번호 안내 이메일 입니다.");
        dto.setMessage("안녕하세요. HOTTHINK 임시비밀번호 안내 관련 이메일 입니다." + "[" + nickName + "]" +"님의 임시 비밀번호는 "
                + tempPassword + " 입니다.");
        updatePassword(tempPassword,userEmail);
        return dto;
    }

    @Transactional
    public void updatePassword(String tempPassword,String userEmail){
        String pw = encoder.encode(tempPassword);
        Long id = userRepository.findByEmail(userEmail).get().getId();
        System.out.println("비번변경 유저 아이디: "+id);
        User persistance=userRepository.findById(id).orElseThrow(()
                ->{return new IllegalArgumentException("회원찾기 실패");
        });
        String rawPassword=pw;
        String encPassword=encoder.encode(rawPassword);
        persistance.setPw(encPassword);
    }

    @Transactional
    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }
    @Transactional
    public void mailSend(MailDto mailDto){
        System.out.println("이멜 전송 완료!");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setFrom(SendMailService.FROM_ADDRESS);
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());

        javaMailSender.send(message);
    }
}
