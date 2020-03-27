package com.ecors.product.DTO;

import com.ecors.core.dto.SellerDTO;
import com.ecors.product.entity.ProductDeliveryDetails;

public class ProductDTO {

	private String productID;
	private String productName;
	private String productImgUrl;
	private int price;
	private int discountedPrice;
	private short discount;
	private ProductDeliveryDetails productDeliveryDetails;
	private ProductDetails productDetails;
	private SellerDTO seller;

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public ProductDeliveryDetails getProductDeliveryDetails() {
		return productDeliveryDetails;
	}

	public void setProductDeliveryDetails(ProductDeliveryDetails productDeliveryDetails) {
		this.productDeliveryDetails = productDeliveryDetails;
	}

	public SellerDTO getSeller() {
		return seller;
	}

	public void setSeller(SellerDTO seller) {
		this.seller = seller;
	}

	public ProductDetails getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(int discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public short getDiscount() {
		return discount;
	}

	public void setDiscount(short discount) {
		this.discount = discount;
	}

	public String getProductImgUrl() {
		return productImgUrl;
	}

	public void setProductImgUrl(String productImgUrl) {
		this.productImgUrl = productImgUrl;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
