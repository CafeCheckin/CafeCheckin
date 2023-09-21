package com.example.CafeTour.user;

import com.example.CafeTour.config.mail.SendMailService;
import com.example.CafeTour.config.mail.MailDto;
import com.example.CafeTour.user.userdto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.mail.MessagingException;
import java.security.Principal;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final SendMailService sendMailService;

    public User create(String username, String email, String password) {
        User user = new User();
        user.setNickName(username);
        user.setEmail(email);
        user.setPw(encoder.encode(password));
        this.userRepository.save(user);
        return user;
    } //회원 추가

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> _user = this.userRepository.findByEmail(username);
        if (_user.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }
        User user = _user.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPw(), authorities);
    }

    public UserResponseDto findByEmail(String email) {
        User user = isError(email);
        return new UserResponseDto(user);
    }

    public Long findPassword(String email) throws MessagingException {
        User user = isError(email);
        MailDto dto = sendMailService.createMailAndChangePassword(user.getEmail(), user.getNickName());
        sendMailService.mailSend(dto);
        return user.getId();
    }

    @Transactional
    public void updateEmail(String email, Long userId) {
        User persistence = isError(email);
        persistence.setEmail(email);
    } //회원 정보 업데이트

    @Transactional
    public void updateNickName(String username, String email) {
        User persistance = isError(email);
        persistance.setNickName(username);
    } //회원 정보 업데이트

    @Transactional
    public void updatePassword(String password, String email) {
        User persistance = isError(email);
        String rawPassword = password;
        String encPassword = encoder.encode(rawPassword);
        persistance.setPw(encPassword);
    } //회원 정보 업데이트

    @Transactional
    public boolean deleteUser(String password, Principal principal) { //회원탈퇴
        User persistence = isError(principal.getName());

        if (encoder.matches(password, persistence.getPw())) {
            userRepository.deleteById(persistence.getId());
            return true;
        }
        return false;
    }

    public boolean doubleCheckEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        /* 유효성 및 중복 검사에 실패한 필드 목록을 받음 */
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    public User isError(String email) {
        User persistence = userRepository.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 회원"));
        return persistence;
    }
}
