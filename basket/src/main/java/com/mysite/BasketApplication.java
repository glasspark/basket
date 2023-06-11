package com.mysite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@SpringBootApplication 애너테이션을 통해 스프링부트의 모든 설정이 관리
@SpringBootApplication
public class BasketApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasketApplication.class, args);
	}

}
