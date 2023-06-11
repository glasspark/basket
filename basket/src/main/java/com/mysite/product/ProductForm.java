package com.mysite.product;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

//추후 작성
@Getter
@Setter
public class ProductForm {

	@NotEmpty(message = "숫자를 입력해 주세요")
	@Size(max = 200)
	private String pd_count;

}
