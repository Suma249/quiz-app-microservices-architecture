package com.learning.question_service.controller;

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

import com.learning.question_service.entity.QuestionEntity;
import com.learning.question_service.entity.QuestionWrapper;
import com.learning.question_service.entity.Response;
import com.learning.question_service.service.QuestionService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/question")
public class QiuestionsController {

	@Autowired
	private final QuestionService service;
	
	@GetMapping("/allQuestions")
	public ResponseEntity<List<QuestionEntity>> getAllQuestions() {
		//return service.getAllQuestions();
		return  service.getAllQuestions();
	}
	
	@GetMapping("/category/{category}")
	public ResponseEntity<List<QuestionEntity>> getQuestionsByCategory(@PathVariable String category){
		return service.gethQuestionsByCategory(category);
	}
	
	@PostMapping("/addQuestion")
	public ResponseEntity<QuestionEntity> addQuestion(@RequestBody QuestionEntity question) {
		return service.addQuestion(question);
		
	}
	
	@GetMapping("/generate")
	public ResponseEntity<List<Integer>>getQuestionsForQuiz(@RequestParam String category, @RequestParam int numOfQuestion){
		return service.getQuestionsForQuiz(category, numOfQuestion);
	}
	
	@PostMapping("/getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
		return service.getQuestionsFromId(questionIds);
	}
	
	@PostMapping("/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
		return service.getScore(responses);
	}
}
