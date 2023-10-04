package com.example.CafeTour.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardJpaRepository {
    private final EntityManager em;

    public List<Board> findAll(){
        return em.createQuery("select b from Board b",Board.class)
                .getResultList();
    }
}
