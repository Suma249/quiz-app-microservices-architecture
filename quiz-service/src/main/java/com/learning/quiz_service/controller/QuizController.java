package com.learning.quiz_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.quiz_service.entity.QuestionWrapper;
import com.learning.quiz_service.entity.QuizDto;
import com.learning.quiz_service.entity.Response;
import com.learning.quiz_service.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	private QuizService service;
	
	@PostMapping("/create")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
		System.out.println("request recieved at quiz sevice for end point quiz/create with data,\n category:"+quizDto.getCategory()+"\n number quetions: "+quizDto.getNumOfQuestions()+"\n title: "+quizDto.getTitle());
		return service.createQuiz(quizDto.getCategory(), quizDto.getNumOfQuestions(), quizDto.getTitle());
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
		return service.getQuizQuestions(id);
	}
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> response){
		return service.calculateResult(id, response);
		
	}
	
}
