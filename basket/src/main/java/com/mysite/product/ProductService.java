package com.mysite.product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mysite.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {

	private final ProductRepository productRepository;
	private final ProductImgRepository imgRepository;

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

	// 해당 product에 해당되는 이미지를 보여준다.
	public ProductImg getImg(Product product) {
		Optional<ProductImg> img = this.imgRepository.findByProduct(product);
		if (img.isPresent()) {

			return img.get();
		}
		return null;
	}

//Stack(상품 수량)이 변경되면 저장(해당 상품 주문시)
	public void updateProduct(Product product, Long productId, Integer updatedStock) {
		product.setStack(updatedStock);
		this.productRepository.save(product);
	}

	// 상품 이미지 등록 기능 구현
	public ProductImg upimg(MultipartFile img_url, long product_id) {
		// 해당 아이디를 가져온다. 그리고 거기에 대한 이미지의 값을 넣는다.
		ProductImg productImg = new ProductImg();

		try {
			// 파일을 저장할 경로와 파일명 설정
			String fileName = img_url.getOriginalFilename(); // 원본 파일 이름을 반환
			String filePath = "src/main/resources/static/img/" + fileName;

			// 파일 저장
			Path targetPath = Path.of(filePath);
			Files.copy(img_url.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

			// 이미지 URL 설정
			productImg.setImg_url("/img/" + fileName);
		} catch (IOException e) {
			// 예외 처리
			e.printStackTrace();
		}

		productImg.setProduct(productRepository.findById(product_id).orElse(null));
		this.imgRepository.save(productImg);

		return productImg;
	}

//상품등록 기능 구현
	public Long pdCreate(String product_detal, String product_name, Integer product_sell_status, Integer price,
			Integer stack) {
		Product product = new Product();
		product.setProduct_detal(product_detal);
		product.setProduct_name(product_name);
		product.setProduct_sell_status(product_sell_status);
		product.setPrice(price);
		product.setStack(stack);

		this.productRepository.save(product);
		return product.getProduct_id();
	}

}
