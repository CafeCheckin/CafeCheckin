package com.example.CafeTour.service;

import com.example.CafeTour.domain.User;
import com.example.CafeTour.domain.UserRole;
import com.example.CafeTour.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService{
    @Autowired
    UserRepository userRepository;
    private final PasswordEncoder encoder;


    public User create(String username, String email, String password) {
        User user = new User();
        user.setNickName(username);
        user.setEmail(email);
        user.setPw(encoder.encode(password));
        this.userRepository.save(user);
        return user;
    }

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
    public void updateUser(String username,String password,String email){
        User persistance=userRepository.findByEmail(email).orElseThrow(()
                ->{return new IllegalArgumentException("회원찾기 실패");
        });
        String rawPassword=password;
        System.out.println(rawPassword);
        String encPassword=encoder.encode(rawPassword);
        persistance.setPw(encPassword);
        persistance.setNickName(username);
    }

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
    }
}
