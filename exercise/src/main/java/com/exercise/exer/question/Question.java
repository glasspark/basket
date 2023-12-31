package com.exercise.exer.question;

import java.time.LocalDateTime;
import java.util.List;

import com.exercise.exer.answer.Answer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {
	//@GeneratedValue 1씩 증가 | strategy = 고유번호 | GenerationType.IDENTITY 해당 컬럼만 독립적인 시퀀스 생성 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //pk
	private Integer id;
	
	@Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;
    
    //Answer 응답(답변) many | question 질문 one DB 에서 ForeignKey 관계가 생성
    //mappedBy 주인 필드 지정 여기서는 "question"
    //cascade :  부모 엔티티에 대한 변경이 자식 엔티티에도 적용되어야 할 경우를 정의
    // CascadeType.REMOVE는 부모 엔티티 삭제 되면 자식 엔티티도 삭제 됨
    //아래에서 "Question" 엔티티 클래스에 answerList라는 이름의 필드 추가하고 
    //"Answer" 엔티티 클래스와의 일대다 관계를 매핑 여기서 Question 이 주인 "Question"이 삭제되면 관련된 모든 "Answer"함께 삭제
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

}
