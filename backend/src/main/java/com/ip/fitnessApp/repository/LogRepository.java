package com.ip.fitnessApp.repository;

import com.ip.fitnessApp.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Integer> {
}
