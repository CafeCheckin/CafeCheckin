package com.example.CafeTour.service;

import com.example.CafeTour.auth.PrincipalDetails;
import com.example.CafeTour.domain.User;
import com.example.CafeTour.domain.UserRole;
import com.example.CafeTour.dto.UserDto;
import com.example.CafeTour.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void updateUser(UserDto userDto){
        userRepository.save(User.toUpdateUser(userDto));
    }
}
