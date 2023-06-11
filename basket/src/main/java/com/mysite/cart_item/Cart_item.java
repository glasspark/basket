package com.mysite.cart_item;

import com.mysite.cart.Cart;
import com.mysite.product.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cart_item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cart_product_id;

	private Integer count;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;

}
