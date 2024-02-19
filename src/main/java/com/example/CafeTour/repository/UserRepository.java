package com.example.CafeTour.repository;

import com.example.CafeTour.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByNickName(String username);
    boolean existsByEmail(String email);
    boolean existsByNickName(String nickName);

}