package com.learning.question_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learning.question_service.entity.QuestionEntity;
import com.learning.question_service.entity.QuestionWrapper;
import com.learning.question_service.entity.Response;
import com.learning.question_service.repo.QuestionRepo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class QuestionService {

	@Autowired
	private QuestionRepo repo;
	
	public ResponseEntity<List<QuestionEntity>> getAllQuestions() {
		try {
		return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<QuestionEntity>> gethQuestionsByCategory(String category) {
		// TODO Auto-generated method stub
		return new ResponseEntity<>(repo.findByCategory(category), HttpStatus.OK);
	}

	public ResponseEntity<QuestionEntity> addQuestion(QuestionEntity question) {
		// TODO Auto-generated method stub
		return new ResponseEntity<>(repo.save(question), HttpStatus.CREATED);
	}

	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, int numOfQuestions) {
Long lnumOfQuestions=Long.valueOf(numOfQuestions);
		System.out.println("in questions ervice");
		List<Integer> questions=(List) repo.findRandomQuestionsByCategory(category)
				.stream().
				limit(lnumOfQuestions).
				collect(Collectors.toList());
		System.out.println("completed question service");
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
		List<QuestionWrapper> wrapper=new ArrayList<>();
		List<QuestionEntity> questions=new ArrayList<>();
		for(Integer id: questionIds)
		{
			questions.add(repo.findById(id).get());
		}
		for(QuestionEntity q: questions)
		{
		  wrapper.add(new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3()));
		}
		return new ResponseEntity<>(wrapper, HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses) {
		int right=0;
		for(Response res: responses) {
			QuestionEntity q=repo.findById(res.getId()).get();
			System.out.println("question: "+res.getId()+" right ans: "+q.getRightAns()+"user answer: "+res.getResponse());
			if(res.getResponse().equals(q.getRightAns()))
				right++;
		}
		return new ResponseEntity<>(right, HttpStatus.OK);
	}
}
