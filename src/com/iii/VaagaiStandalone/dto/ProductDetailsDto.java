package com.iii.VaagaiStandalone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDetailsDto {
	private int productID;
	private String productName;
	private String productImage;
	private String productPackSize;
	private String productAttribute;

}
