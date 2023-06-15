package com.mysite.cart_item;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.cart.Cart;
import com.mysite.product.Product;

public interface Cart_itemRepository extends JpaRepository<Cart_item, Integer> {

	//주어진 Cart와 Product로 매칭되는 Cart_item을 반환
	Cart_item findByCartAndProduct(Cart cart, Product product);

	// 아래 추 후 수정필요 Optional => 값이 있을지도 없을지도 모르는 경우 사용
	Optional<Cart_item> findByCart(Cart cart);



}
