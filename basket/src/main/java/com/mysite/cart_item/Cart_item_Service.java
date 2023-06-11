package com.mysite.cart_item;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class Cart_item_Service {

	private final Cart_itemRepository cart_itemRepository;

	//질문 목록을 조회하여 리턴하는 getList 메서드를 추가
	public List<Cart_item> getList() {
		return this.cart_itemRepository.findAll();
	}
	
	
	public void addCart() {}

}

