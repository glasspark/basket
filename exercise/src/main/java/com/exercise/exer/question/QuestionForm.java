package com.exercise.exer.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

//화면에서 전달되는 입력 값을 검증
@Getter
@Setter
public class QuestionForm {

	@NotEmpty(message = "제목은 필수항목입니다.")
	@Size(max = 200)
	private String subject;

	@NotEmpty(message = "내용은 필수항목입니다.")
	private String content;

}
