package com.ip.fitnessApp.repository;

import com.ip.fitnessApp.model.Dnevnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DnevnikRepository extends JpaRepository<Dnevnik, Integer> {
}
