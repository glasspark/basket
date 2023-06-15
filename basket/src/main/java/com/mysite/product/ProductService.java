package com.mysite.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {

	private final ProductRepository productRepository;

	public List<Product> getList() {
		return this.productRepository.findAll();

	}

//product_id값이 없는 경우에 DataNotFoundException을 발생
	public Product getProduct(Long product_id) {
		Optional<Product> product = this.productRepository.findById(product_id);
		if (product.isPresent()) {
			return product.get();
		} else {
			// 오류 발생 시 DataNotFoundException클래스에 던짐
			throw new DataNotFoundException("product_id not found");
		}
	}

//Stack(상품 수량)이 변경되면 저장(해당 상품 주문시)
	public void updateProduct(Product product, Long productId, Integer updatedStock) {
		product.setStack(updatedStock);
		this.productRepository.save(product);
	}

}
