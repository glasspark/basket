package com.mysite.cart;

/*http://localhost:8081/
*/
import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.cart_item.Cart_item_Service;
import com.mysite.product.Product;
import com.mysite.product.ProductService;
import com.mysite.user.User;
import com.mysite.user.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/cart")
@RequiredArgsConstructor // 생성자 생성
@Controller
public class CartController {

	private final CartService cartService;
	private final ProductService productService;
	private final UserService userService;
	private final Cart_item_Service cart_item_Service;

	@GetMapping(value = "/addCart/{product_id}/{requestedQuantity}")
	public String addCart(@PathVariable("product_id") Long product_id,
			@PathVariable("requestedQuantity") Integer requestedQuantity, Principal principal) {

		Product product = this.productService.getProduct(product_id); // 제품 아이디 가져온다.
		int count = requestedQuantity;
		String username = principal.getName(); // 유저의 이름을 가져온다.

		// 가져온 username으로 해당 user가 있는지 없는지 확인한다.
		User user = this.userService.getUserIdByUsername(username);

		this.cartService.addCart(user, count); // 해당 유저 장바구니에 추가

		// cart에 담겨져 있는 유저의 user_id값을 가져온다.
		Integer cartUser = this.cartService.getCartIdByUsername(user);

		// 해당 user_id의 cart_id를 가져온다.
		Cart CartId = this.cartService.getUserCartId(cartUser);

		// 카트 아이디가져와서 카트에 담음
		this.cart_item_Service.addCartItem(count, product, CartId);

		return "redirect:/product/list";

	}

	@PostMapping("/totalCount")
	public ResponseEntity<Integer> totalCount(@RequestParam("user") String username, @RequestParam("count") int count) {

		// 가져온 username으로 해당 user가 있는지 없는지 확인한다.
		User user = this.userService.getUserIdByUsername(username);

		int result = this.cartService.addCart(user, count);

		return ResponseEntity.ok(result);
	}

}
