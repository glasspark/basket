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

	public User getUser(String username) {
		Optional<User> user = this.userRepository.findByusername(username);
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}

	// 해당 username의 입력받아서 pk인 userID를 받게된다.
	public Long getUserIdByUsername(String username) {
		Optional<User> user = userRepository.findByusername(username);
		if (user != null) {
			return user.get().getUser_id();
		}
		return null; // 사용자 정보가 없을 경우
	}

	// 위에 Long의 형태인 것을 User로 변경한다.
	public User getUserById(Long userId) {
		return userRepository.getReferenceById(userId);
	}

}
