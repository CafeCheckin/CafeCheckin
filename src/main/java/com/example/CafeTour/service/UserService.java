package com.example.CafeTour.service;

import com.example.CafeTour.domain.User;
import com.example.CafeTour.domain.UserRole;
import com.example.CafeTour.repository.UserRepository;
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

import java.security.Principal;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService{
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

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

    public User findByEmail(String email){
        Optional<User> userOptional=userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            User user=userOptional.get();
            return user;
        }
        return null;
    }

    @Transactional
    public void updateEmail(String email,Long userId){
        User persistance=userRepository.findById(userId).orElseThrow(()
                ->{return new IllegalArgumentException("회원찾기 실패");
        });
        persistance.setEmail(email);
    } //회원 정보 업데이트

    @Transactional
    public void updateNickName(String username,Long userId){
        User persistance=userRepository.findById(userId).orElseThrow(()
                ->{return new IllegalArgumentException("회원찾기 실패");
        });
        persistance.setNickName(username);
    } //회원 정보 업데이트

    @Transactional
    public void updatePassword(String password,Long userId){
        User persistance=userRepository.findById(userId).orElseThrow(()
                ->{return new IllegalArgumentException("회원찾기 실패");
        });
        String rawPassword=password;
        String encPassword=encoder.encode(rawPassword);
        persistance.setPw(encPassword);
    } //회원 정보 업데이트

    @Transactional
    public boolean deleteUser(String password, Principal principal) {
        User persistance = userRepository.findByEmail(principal.getName()).orElseThrow(()
                -> {
            return new IllegalArgumentException("회원찾기 실패");
        });

        if(encoder.matches(password,persistance.getPw())){
            userRepository.deleteById(persistance.getId());
            return true;
        }
        return false;
    } //회원탈퇴

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
}
