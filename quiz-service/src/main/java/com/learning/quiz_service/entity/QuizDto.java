package com.learning.quiz_service.entity;

import lombok.Data;

@Data
public class QuizDto {
	private String category;
	private String title;
	private int numOfQuestions;
}
