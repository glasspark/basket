package com.exercise.exer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
//@Setter final로 정의해서 의미 없음
public class HelloLombok {

	private final String Hello;
	private final int lombok;

	public static void main(String[] args) {
		HelloLombok helloLombok = new HelloLombok("헬로", 5);
		System.out.println(helloLombok.getHello());
		System.out.println(helloLombok.getLombok());

	}
}
