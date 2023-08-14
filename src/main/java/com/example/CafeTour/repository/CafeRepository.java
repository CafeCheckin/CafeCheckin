package com.example.CafeTour.repository;

import com.example.CafeTour.domain.CafeInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CafeRepository extends JpaRepository<CafeInformation, Long> {
    @Override
    List<CafeInformation> findAll();

    List<CafeInformation> findByAddressContaining(String name);
}
