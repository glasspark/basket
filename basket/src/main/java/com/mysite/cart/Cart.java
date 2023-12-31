package com.mysite.cart;

import java.util.ArrayList;
import java.util.List;

import com.mysite.cart_item.Cart_item;
import com.mysite.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cart_id;

	@OneToOne
	@JoinColumn(name = "user_id") // 구매자
	private User user;

	private int count; // 카트에 담긴 총 상품 개수

	// one = cart
	@OneToMany(mappedBy = "cart")
	private List<Cart_item> cartItems = new ArrayList<>();

	

}
