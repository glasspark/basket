package com.mysite.cart_item;

import java.util.List;

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

	//카트에 아이템을 저장한다
	public Cart_item addCartItem(Integer count, Product product, Cart cart) {
		Cart_item cart_item = new Cart_item();
		cart_item.setCount(count);
		cart_item.setProduct(product);
		cart_item.setCart(cart); 

		this.cart_itemRepository.save(cart_item);
		return cart_item;
	}
	
	
	
	
	

}
