package com.example.CafeTour.service;

import com.example.CafeTour.domain.User;
import com.example.CafeTour.dto.MailDto;
import com.example.CafeTour.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class SendMailService {
    private final UserRepository userRepository;
    private static final String FROM_ADDRESS = "seoulcafecheckin@gmail.com";
    private final JavaMailSender javaMailSender;
    private final PasswordEncoder encoder;

    @Transactional
    public MailDto createMailAndChangePassword(String userEmail, String nickName) {
        String tempPassword = getTempPassword();
        MailDto dto = new MailDto();
        dto.setAddress(userEmail);
        dto.setTitle(nickName + "님의 CafeCheckIn 임시비밀번호 안내 이메일 입니다.");
        dto.setMessage("<h1>임시비밀번호 발급</h1>" +
                "<br/>" + nickName + "님 " +
                "<br/>비밀번호 찾기를 통한 임시 비밀번호입니다." +
                "<br/>임시비밀번호 :   <h2>" + tempPassword + "</h2>" +
                "<br/>로그인 후 비밀번호 변경을 해주세요." +
                "<a href='http://localhost:8080/home'>로그인 하러가기</a>");
        updatePassword(tempPassword, userEmail);
        return dto;
    }

    @Transactional
    public void updatePassword(String tempPassword, String userEmail) {
        Long id = userRepository.findByEmail(userEmail).get().getId();
        System.out.println("비번변경 유저 아이디: " + id);
        User persistance = userRepository.findById(id).orElseThrow(()
                -> {
            return new IllegalArgumentException("회원찾기 실패");
        });
        String rawPassword = tempPassword;
        String encPassword = encoder.encode(rawPassword);
        persistance.setPw(encPassword);
    }

    @Transactional
    public String getTempPassword() {
        char[] charSet = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    @Transactional
    public void mailSend(MailDto mailDto) throws MessagingException {
        System.out.println("이멜 전송 완료!");
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
        messageHelper.setTo(mailDto.getAddress());
        messageHelper.setFrom(SendMailService.FROM_ADDRESS);
        messageHelper.setSubject(mailDto.getTitle());
        messageHelper.setText(mailDto.getMessage(), true);
        javaMailSender.send(message);
    }
}
