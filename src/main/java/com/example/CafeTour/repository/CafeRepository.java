package com.example.CafeTour.repository;

import com.example.CafeTour.domain.CafeInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.spi.LocaleNameProvider;

public interface CafeRepository extends JpaRepository<CafeInformation, Long> {
    Optional<CafeInformation> findByAll();
}
