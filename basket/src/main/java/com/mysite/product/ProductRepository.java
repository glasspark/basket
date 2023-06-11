package com.mysite.product;

import org.springframework.data.jpa.repository.JpaRepository;

//<해당 엔티티클래스, PK의 속성 타입>
public interface ProductRepository extends JpaRepository<Product, Long> {

}
