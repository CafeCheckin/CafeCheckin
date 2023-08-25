package com.example.CafeTour.repository;

import com.example.CafeTour.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
    @Override
    Optional<Board> findById(Long aLong);

    @Modifying
    @Query("update Board b set b.clicks = b.clicks + 1 where b.id = :id")
    void updateView(Long id);
}
