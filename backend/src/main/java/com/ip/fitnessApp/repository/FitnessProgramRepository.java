package com.ip.fitnessApp.repository;

import com.ip.fitnessApp.model.FitnessProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface FitnessProgramRepository extends JpaRepository<FitnessProgram, Integer> {
    Page<FitnessProgram> findAll(Pageable pageable);

    List<FitnessProgram> findByCreatedAtAfter(LocalDateTime dateTime);
}
