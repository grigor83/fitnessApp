package com.ip.fitnessApp.repository;

import com.ip.fitnessApp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
