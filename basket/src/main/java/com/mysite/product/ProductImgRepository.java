package com.mysite.product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImgRepository extends JpaRepository<ProductImg, Integer> {

	Optional<ProductImg> findByProduct(Product product);

}
