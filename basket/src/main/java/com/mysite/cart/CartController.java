package com.mysite.cart;

/*http://localhost:8081/
*/
import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

		Long userID = this.userService.getUserIdByUsername(username);
		User user = this.userService.getUserById(userID); // ID로 유저 정보를 찾는다.

		this.cartService.addCart(user); // 해당 유저 장바구니에 추가

		// cart에 담겨져 있는 유저의 user_id값을 가져온다.
		Integer cartUser = this.cartService.getCartIdByUsername(user);
		// 해당 user_id의 cart_id를 가져온다.
		Cart CartId = this.cartService.getUserCartId(cartUser);
		
		System.out.println("===========");
		System.out.println(cartUser);
		System.out.println(userID);
		System.out.println(user);
		System.out.println("===========");
		

		// 카트 아이디를 가져와야 한다.
		this.cart_item_Service.addCartItem(count, product, CartId);

		return "redirect:/product/list";
		
	}

}
