package com.mysite.product;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequestMapping("/product")
@RequiredArgsConstructor // 생성자 생성
@Controller
public class ProductController {

	// private final ProductRepository productRepository;

	// ↓↓ productService에서 서비스와 한거 가져옴 아래 getList()방식으로 사용한다. i
	private final ProductService productService;

	@GetMapping("/list") // 이 경로에 get요청 오면 실행
	public String list(Model model) {
		// List<Product> productList = this.productRepository.findAll();
		List<Product> productList = this.productService.getList();
		// "productList" = 키(이름지정) | productList =값(위의 List<Product> productList)
		model.addAttribute("productList", productList);// 이제 다른데서 참조하여 사용가능
		return "product_List";

	}

	@GetMapping(value = "/detail/{product_id}")
	public String detail(Model model, @PathVariable("product_id") Long product_id) {
		Product product = this.productService.getProduct(product_id);
		model.addAttribute("product", product);
		return "product_detail";
	}

	//해당 제품의 수량 확인
	@GetMapping(value = "/detail/{product_id}/order")
	public String ordercheck(Model model, @PathVariable("product_id") Long product_id,
			@RequestParam("pd_count") String pd_count) {
		Product product = this.productService.getProduct(product_id); // 제품 아이디 가져온다.

		// 주문 가능 여부를 확인
		int availableQuantity = product.getStack(); // 재고
		int requestedQuantity = Integer.parseInt(pd_count); // 주문수

		Integer message;
		if (availableQuantity >= requestedQuantity) {
			message = 1;
		} else {
			message = 2;
		}

		model.addAttribute("product", product);
		model.addAttribute("message", message);
		model.addAttribute("requestedQuantity", requestedQuantity);
		return "order_check";
	}

	//주문을 하면 수량이 줄어들도록 설정함
	@GetMapping(value = "/complete/{product_id}/{requestedQuantity}")
	public String complete(@PathVariable("product_id") Long product_id,
			@PathVariable("requestedQuantity") Integer requestedQuantity) {
		Product product = this.productService.getProduct(product_id);
		int updatedStock = (product.getStack() - requestedQuantity);
		this.productService.updateProduct(product, product_id, updatedStock);

		return "redirect:/product/list";
	}

}
