package com.mysite.cart_item;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequestMapping("/cartItem")
@RequiredArgsConstructor // 생성자 생성
@Controller
public class Cart_item_Controller {

	private final Cart_item_Service cart_item_Service;

}
