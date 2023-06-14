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

	//user의 카트 번호를 가져온다. 
	public Integer getCartIdByUsername(User user) {
		Optional<Cart> cart = cartRepository.findByUser(user);
		if (cart.isPresent()) {
			return cart.get().getCart_id();
		}
		// 카트가 없을 경우 처리
		return null;
	}

	public Cart getUserCartId(Integer userID) {
		return cartRepository.findById(userID).orElse(null);
	}

	//유저의 카트 생성
	public void addCart(User user, int count) {

		// 카트에 해당 유저의 아이디가 있는지 확인
		Optional<Cart> findCart = cartRepository.findByUser(user);

		// 만약 유저에게 장바구니가 존재하지 않는다면 생성해준다.
		if (!findCart.isPresent()) {
			Cart cart = new Cart();
			cart.setCount(count);
			cart.setUser(user); // 유저 객체
			this.cartRepository.save(cart); // 저장
		} else {
			// 카트가 존재한다면 카트를 가져와서 수량을 추가
			Cart cart = findCart.get();
			cart.setCount(cart.getCount() + count);// 수량 추가
			this.cartRepository.save(cart); // 저장
		}

	}
}
