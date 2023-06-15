package com.mysite.user;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mysite.cart.Cart;
import com.mysite.cart.CartController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public User create(String username, String email, String password, String address) {

		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setAddress(address);

		// 암호화를 위해 시큐리티의 BCryptPasswordEncoder 클래스를 사용
		user.setPassword(passwordEncoder.encode(password));
		this.userRepository.save(user);
		return user;

	}

	// 해당 username을 이력 받아서 name을 찾고 객체로 저장한다.
	public User getUserIdByUsername(String username) {
		Optional<User> userOptional = userRepository.findByusername(username);
		return userOptional.orElse(null); // 사용자 정보가 없을 경우 null 반환}

	}

}
