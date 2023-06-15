package com.mysite.cart_item;

import java.security.Principal;
import java.util.List;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.cart.Cart;
import com.mysite.cart.CartService;
import com.mysite.user.User;
import com.mysite.user.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/cart_item")
@RequiredArgsConstructor // 생성자 생성
@Controller
public class Cart_item_Controller {

	// 이부분 단순 확인용이기 때문에 추후에 cart/ cart_item 정보 불러오는거 수저해야함
	private final Cart_item_Service cart_item_Service;
	private final CartService cartService;
	private final UserService userService;

	// 장바구니 리스트를 보여준다
	@GetMapping("/itemList")
	public String itemList(Model model, Principal principal) {
		try { // 예외처리 추후 수정
				// 해당 유저의 이름을 가져온다. String형
			String username = principal.getName();

			// 해당 유저의 정보를 가져와 User user의 형식으로 저장
			User user = this.userService.getUserIdByUsername(username);

			// cart에 담겨져 있는 유저의 user_id값을 가져온다.
			Integer cartUser = this.cartService.getCartIdByUsername(user);

			// 해당 유저의 Cart 객체를 가져온다.
			Cart cart = this.cartService.getUserCartId(cartUser);
			int cartCount = cart.getCount(); // 장바구니 수량 반환

			List<Cart_item> cartItemList = this.cart_item_Service.getList();

			// cart model
			model.addAttribute("cartCount", cartCount);
			model.addAttribute("username", username);

			// cart_item model
			model.addAttribute("cartUser", cartUser);
			model.addAttribute("cartItemList", cartItemList);

			return "cart_list";
		} catch (InvalidDataAccessApiUsageException ex) {

			model.addAttribute("errorMessage", "장바구니에 아무것도 없습니다.");
			return "cart_list";
		}
	}

	/*
	 * @PostMapping("/updateCount") public ResponseEntity<Integer>
	 * updateCount(@RequestParam("cartItemId") Integer cartItemId,
	 * 
	 * @RequestParam("newCount") int count) {
	 * 
	 * int result = 1; return ResponseEntity.ok(result);
	 * 
	 * // 응답 생성 return ResponseEntity.ok("Quantity updated successfully");
	 * 
	 * }
	 */

	@PostMapping("/updateCount")
	public ResponseEntity<Integer> updateCount(@RequestParam("cart_product_id") Integer cart_product_id,
			@RequestParam("count") int count) {

		//해당 카트 아이템에 count,cart_product_id 를 넣는다.
		int result = this.cart_item_Service.updateCount(cart_product_id, count);

		return ResponseEntity.ok(result);
	}

}
