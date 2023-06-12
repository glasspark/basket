package com.mysite.cart;

import java.util.List;
import java.util.Optional;

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

	public Integer getCartIdByUsername(User user) {

		Optional<Cart> cart = cartRepository.findByUser(user);
		if (cart.isPresent()) {
			return cart.get().getCart_id();
		}
		// 카트가 없을 경우 처리
		return null;
	}

	public Cart getUserCartId(Integer userId) {
		return cartRepository.findById(userId).orElse(null);
	}

	// !! 여기에 조건을 걸어야 하나..?
	// 카트를 추가하는 것 (여기서 중복시 혹은 장바구니 없을 시 생성하는 코드 작성)
	// public Cart addCart(User user)
	public void addCart(User user) {

		Optional<Cart> findCart = cartRepository.findByUser(user);
		// 만약 유저에게 장바구니가 존재하지 않는다면 생성해준다.   
		if (!findCart.isPresent()) { 
			Cart cart = new Cart();
			// cart.setCount(count);
			cart.setUser(user);
			this.cartRepository.save(cart);

		}

		// 유저에게 장바구니가 있고 상품이 존재하지 않는다면 상품을 추가해준다.

		// 유저에게 장바구니가 있고 상품이 있는 경우라면 상품을 업데이트 해준다.

	}
}
