package com.learning.question_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionWrapper {
	
	private Integer id;
	private String questionTitle;
	private String option1;
	private String option2;
	private String option3;
}
