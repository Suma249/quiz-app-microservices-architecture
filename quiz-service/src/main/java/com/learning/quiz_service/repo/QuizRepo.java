package com.learning.quiz_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.quiz_service.entity.QuizEntity;

@Repository
public interface QuizRepo extends JpaRepository<QuizEntity, Integer> {

}
