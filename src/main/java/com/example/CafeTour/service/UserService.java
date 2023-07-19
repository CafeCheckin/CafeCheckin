package com.example.CafeTour.service;

import com.example.CafeTour.domain.User;
import com.example.CafeTour.dto.UserDto;
import com.example.CafeTour.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

   /* public void save(UserDto userDto) {
        User user = userRepository.save(User.toUser(userDto));
    }
    */

    public void save(User user){
        userRepository.save(user);
    }
}
