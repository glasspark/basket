package com.mysite.cart;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mysite.user.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartService {

	private final CartRepository cartRepository;

	// 카드에 대한 모든 것을 출력
	public List<Cart> getList() {
		return this.cartRepository.findAll();

	}

	//카트를 추가하는 것
	public Cart addCart(User user, int count) {
		Cart cart = new Cart();
		cart.setCount(count);
		cart.setUser(user);
		this.cartRepository.save(cart);
		return cart;

	}

}
