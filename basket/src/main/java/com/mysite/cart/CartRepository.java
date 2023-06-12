package com.mysite.cart;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.user.User;


public interface CartRepository extends JpaRepository<Cart, Integer> {
	
	  Optional<Cart> findByUser(User user);
}
