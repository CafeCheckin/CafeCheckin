package com.example.CafeTour.repository;

import com.example.CafeTour.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
    //Page<Board> findByAll(PageRequest pageRequest);

    @Override
    Optional<Board> findById(Long aLong);
}
