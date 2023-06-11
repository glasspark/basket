package com.mysite.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

}
