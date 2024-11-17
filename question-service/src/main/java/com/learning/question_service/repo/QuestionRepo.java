package com.learning.question_service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.learning.question_service.entity.QuestionEntity;

@Repository
public interface QuestionRepo extends JpaRepository<QuestionEntity, Integer> {

	List<QuestionEntity> findByCategory(String category);

	@Query(value="SELECT q.id FROM questions q Where q.category=:category ORDER BY RAND()", nativeQuery=true)
   List<Integer> findRandomQuestionsByCategory(String category);

}
