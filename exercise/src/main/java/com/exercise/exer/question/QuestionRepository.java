package com.exercise.exer.question;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

//리포지터리로 만들기 위해 JpaRepository 인터페이스를 상속
//Question 엔티티의 PK(Primary Key) 속성인 id의 타입은 Integer 
//<Question, Integer> 이걸로 해당 엔티티의 리포지터리를 만든다. 
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
	   Question findBySubject(String subject);
	    Question findBySubjectAndContent(String subject, String content);
	    List<Question> findBySubjectLike(String subject);
	    Page<Question> findAll(Pageable pageable);
	
}
