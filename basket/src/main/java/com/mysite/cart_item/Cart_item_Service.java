package com.mysite.cart_item;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.cart.Cart;
import com.mysite.product.Product;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class Cart_item_Service {

	private final Cart_itemRepository cart_itemRepository;

	// 상품 리스트들을 출력하는 메서드
	public List<Cart_item> getList() {
		return this.cart_itemRepository.findAll();
	}

	// Cart와 연관된 Cart_item을 조회
	public Optional<Cart_item> getCartItemByCart(Cart cart) {
		return cart_itemRepository.findByCart(cart);
	}

	// 카트에 아이템을 저장한다
	public Cart_item addCartItem(Integer count, Product product, Cart cartId) {
		// 장바구니에 상품이 있는지 없는지 확인
		Cart_item findCartIdAndProduct = cart_itemRepository.findByCartAndProduct(cartId, product);

		// 상품이 있고 수량이 있다면 cart에 수량을 증가시킨다
		if (findCartIdAndProduct != null) {
			findCartIdAndProduct.setCount(findCartIdAndProduct.getCount() + count);
			cart_itemRepository.save(findCartIdAndProduct);
			return findCartIdAndProduct;
		}

		Cart_item cart_item = new Cart_item();
		cart_item.setCount(count);
		cart_item.setProduct(product);
		cart_item.setCart(cartId);

		this.cart_itemRepository.save(cart_item);
		return cart_item;
	}

	// 해당 변경사항이 생기면 현재 유저의 카트ID 에 값을 변경해준다.(예외처리 해야함)
	public Integer updateCount(Integer cart_product_id, int count) {
		Optional<Cart_item> findId = cart_itemRepository.findById(cart_product_id);

		if (findId.isPresent()) {
			Cart_item cart_item = findId.get();
			cart_item.setCount(count); // 값을 수정
			cart_itemRepository.save(cart_item);
		} else {
			return 2; // 실패 2
		}
		return 1; // 성공 1
	}

	public Integer deleteCartItem(Integer cart_product_id) {
		Optional<Cart_item> findId = cart_itemRepository.findById(cart_product_id);

		if (findId.isPresent()) {
			Cart_item cart_item = findId.get();
			cart_itemRepository.delete(cart_item);
		} else {
			return 2; // 실패 2
		}
		return 1; // 성공 1

	}

}
