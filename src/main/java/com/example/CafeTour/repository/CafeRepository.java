package com.example.CafeTour.repository;

import com.example.CafeTour.domain.CafeInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CafeRepository extends JpaRepository<CafeInformation, Long> {
    @Override
    List<CafeInformation> findAll();

    List<CafeInformation> findByAddressContaining(String name);

}
