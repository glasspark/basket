package com.mysite.product;

import java.io.Console;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequestMapping("/product")
@RequiredArgsConstructor // 생성자 생성
@Controller
public class ProductController {

	private final ProductService productService;

	@GetMapping("/list")
	public String list(Model model) {
		List<Product> productList = this.productService.getList();
		model.addAttribute("productList", productList);
		return "product_List";

	}

	// 해당 product_id 상품에 대한 리스트를 보여준다.
	@GetMapping(value = "/detail/{product_id}")
	public String detail(Model model, @PathVariable("product_id") Long product_id) {
		Product product = this.productService.getProduct(product_id);

		ProductImg img = this.productService.getImg(product);

		model.addAttribute("img", img);
		model.addAttribute("product", product);

		System.out.println("=============");
		System.out.println(img);
		System.out.println("=============");

		return "product_detail";
	}

	// 해당 제품의 수량 확인 및 입력값 검가
	@GetMapping(value = "/detail/{product_id}/order")
	public String ordercheck(Model model, @PathVariable("product_id") Long product_id,
			@RequestParam("pd_count") String pd_count) {
		Product product = this.productService.getProduct(product_id); // 제품 아이디 가져온다.

		// 주문 가능 여부를 확인
		int availableQuantity = product.getStack(); // 재고
		int requestedQuantity = Integer.parseInt(pd_count); // 주문수

		Integer message;
		if (availableQuantity >= requestedQuantity) {
			message = 1; // 주문, 장바구니 가능
		} else {
			message = 2; // 불가능
		}

		model.addAttribute("product", product);
		model.addAttribute("message", message);
		model.addAttribute("requestedQuantity", requestedQuantity);
		return "order_check";
	}

	// 주문을 하면 수량이 줄어들도록 설정함
	@GetMapping(value = "/complete/{product_id}/{requestedQuantity}")
	public String complete(@PathVariable("product_id") Long product_id,
			@PathVariable("requestedQuantity") Integer requestedQuantity) {
		Product product = this.productService.getProduct(product_id);
		int updatedStock = (product.getStack() - requestedQuantity);
		this.productService.updateProduct(product, product_id, updatedStock);

		return "redirect:/product/list";
	}

	// 상품 등록하러 오면 보여주는 페이지
	@GetMapping("/addproduct")
	public String showAddPro() {
		return "add_product";
	}

	// 상품 등록
	@PostMapping("/addproduct") // name과 연결한다.
	public String addproduct(@RequestParam("img_url") MultipartFile img_url,
			@RequestParam("product_name") String product_name, @RequestParam("product_detal") String product_detal,
			@RequestParam("product_sell_status") Integer product_sell_status, @RequestParam("price") Integer price,
			@RequestParam("stack") Integer stack) {

		long Product_id = productService.pdCreate(product_detal, product_name, product_sell_status, price, stack);

		productService.upimg(img_url, Product_id);

		return "redirect:/product/list";
	}

}
