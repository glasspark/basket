package com.mysite.cart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequestMapping("/cart")
@RequiredArgsConstructor // 생성자 생성
@Controller
public class CartController {

	private final CartService cartService;
	
	
	
	

}
