package com.ip.fitnessApp.repository;

import com.ip.fitnessApp.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
