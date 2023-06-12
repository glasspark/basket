package com.mysite.cart_item;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequestMapping("/cart_item")
@RequiredArgsConstructor // 생성자 생성
@Controller
public class Cart_item_Controller {

	private final Cart_item_Service cart_item_Service;

	//장바구니 리스트를 보여준다
	@GetMapping("/itemList")
	public String itemList(Model model) {
		List<Cart_item> cartItemList = this.cart_item_Service.getList();
		model.addAttribute("cartItemList", cartItemList);
		return "cart_list";

	}

}
