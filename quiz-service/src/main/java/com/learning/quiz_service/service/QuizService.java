package com.learning.quiz_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learning.quiz_service.entity.*;
import com.learning.quiz_service.feign.QuizInterfaceFeign;
import com.learning.quiz_service.repo.QuizRepo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class QuizService {

	@Autowired
	private QuizRepo quizRepo;
	
	@Autowired
	private QuizInterfaceFeign feign;


	public ResponseEntity<String> createQuiz(String category,int numOfQuestions, String quizTitle) {
		List<Integer> questions=feign.getQuestionsForQuiz(category, numOfQuestions).getBody();
		QuizEntity quiz=new QuizEntity();
		quiz.setTitle(quizTitle);
		quiz.setQuestions(questions);
		quizRepo.save(quiz);
		return new ResponseEntity<>("success", HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		// TODO Auto-generated method stub
		
		QuizEntity quiz=quizRepo.findById(id).get();
		List<Integer> questionIds=quiz.getQuestions();
		ResponseEntity<List<QuestionWrapper>> questions=feign.getQuestionsFromId(questionIds);
		return questions;
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> response) {
		
		return feign.getScore(response);
	}
	
	
}
