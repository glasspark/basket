package com.exercise.exer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.exercise.exer.question.QuestionService;

import jakarta.transaction.Transactional;

@SpringBootTest
class ExerciseApplicationTests {

	@Autowired
	private QuestionService questionService;

	// 메서드가 종료될 때까지 DB 세션이 유지
	@Transactional
	@Test
	void testJpa() {

		for (int i = 1; i <= 50; i++) {
			String subject = String.format("테스트 데이터입니다:[%03d]", i);
			String content = "내용무";
			this.questionService.create(subject, content);
		}

	}
}
