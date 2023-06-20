package com.mysite.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProductImg {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int img_id;

	private String img_url;

	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;

}
