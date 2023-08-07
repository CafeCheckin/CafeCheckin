package com.example.CafeTour.repository;

import com.example.CafeTour.domain.Board;
import com.example.CafeTour.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
    @Override
    Optional<Board> findById(Long aLong);

    @Query(value = "select u.nickName from User u,Board b where u.id=:id")
    Optional<User> findByNickname(@Param("id")Long id);

}
