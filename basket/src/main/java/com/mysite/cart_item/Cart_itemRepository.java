package com.mysite.cart_item;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.cart.Cart;
import com.mysite.product.Product;

public interface Cart_itemRepository extends JpaRepository<Cart_item, Integer> {

	Cart_item findByCartAndProduct(Cart cart, Product product);

	// 아래 추 후 수정필요
	Optional<Cart_item> findByCart(Cart cart);



}
