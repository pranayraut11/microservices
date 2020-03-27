package com.ecors.product.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecors.core.dto.ImageDTO;
import com.ecors.core.dto.OrderSummary;
import com.ecors.core.dto.ProductDTO;
import com.ecors.core.dto.ProductDetails;
import com.ecors.core.enums.ProductSearchCriteria;
import com.ecors.core.exception.NotFoundException;
import com.ecors.core.utility.ModelMapperUtils;

import com.ecors.product.entity.Product;
import com.ecors.product.entity.ProductImages;
import com.ecors.product.entity.ProductSubCategory;
import com.ecors.product.repository.ProductRepository;
import com.ecors.product.repository.ProductSubCatgoryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductSubCatgoryRepository productSubCatgoryRepository;

	@Override
	public ProductDTO get(int id, boolean active) throws JsonMappingException, JsonProcessingException {
		Optional<Product> productOpt = productRepository.findById(id);
		if (productOpt.isPresent()) {
			Product prod = productOpt.get();
			ProductDTO productDTO = ModelMapperUtils.map(prod, ProductDTO.class);
			ArrayList<ProductImages> images = new ArrayList<ProductImages>(prod.getProductImages());
			ProductDetails productDetails = new ProductDetails();
			productDetails.setImages(ModelMapperUtils.mapAll(images, ImageDTO.class));
			productDetails.setHighlights(ModelMapperUtils.mapJson(prod.getProductDetails().getHighlights()));
			productDTO.setProductDetails(productDetails);
			return productDTO;
		}
		throw new NotFoundException("Product", "" + id);

	}

	@Override
	public List<ProductDTO> getAllBySubCategory(String subCategory, boolean inActive, Pageable page) {
		Optional<List<ProductSubCategory>> producs = productSubCatgoryRepository.findBySubCategory(subCategory);
		return mapSubClass(producs.get());

	}

	@Override
	public OrderSummary getProductOrderSummary(List<Integer> productIds) {
		List<ProductDTO> list = getProductsByIds(productIds, ProductSearchCriteria.ID);
		int totalAmmount = list.stream().mapToInt(product -> product.getDiscountedPrice()).sum();
		int savedAmmount = list.stream().mapToInt(product -> product.getPrice() - product.getDiscountedPrice()).sum();
		OrderSummary summary = new OrderSummary();
		summary.setSavedAmmount(savedAmmount);
		summary.setTotalAmmount(totalAmmount);
		summary.setProduct(list);
		return summary;
	}

	@Override
	public List<ProductDTO> getProductsByIds(List<Integer> productIds, ProductSearchCriteria by) {
		Iterable<Product> productList = productRepository.findAllById(productIds);
		return ModelMapperUtils.mapAll(
				StreamSupport.stream(productList.spliterator(), false).collect(Collectors.toList()), ProductDTO.class);
	}

	public static List<ProductDTO> mapSubClass(final List<ProductSubCategory> entityList) {
		return entityList.stream().map(entity -> ModelMapperUtils.map(entity.getProduct(), ProductDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public String addProduct(ProductDTO productDTO, String sellerID) {

		Optional<Product> productOpt = productRepository.findByProductIDAndSellerId(productDTO.getProductID(),
				sellerID);
		Product product = null;
		if (productOpt.isPresent()) {
			product = updateProduct(productDTO, productOpt.get());
		} else {
			product = saveProduct(productDTO, sellerID);
		}

		return productRepository.save(product).getProductId();
	}

	private Product saveProduct(ProductDTO productDTO, String sellerId) {
		Product product = ModelMapperUtils.map(productDTO, Product.class);
		product.setProductId(UUID.randomUUID().toString());
		product.setSellerId(sellerId);
		product.setProductImages(getProductImages(productDTO));
		return product;

	}

	private Product updateProduct(ProductDTO productDTO, Product product) {

		ModelMapperUtils.map(productDTO, product);
		// TO-DO delete images
		product.setProductImages(getProductImages(productDTO));
		return product;
	}

	private Set<ProductImages> getProductImages(ProductDTO productDTO) {
		if (productDTO.getProductDetails() != null && productDTO.getProductDetails().getImages() != null
				&& !productDTO.getProductDetails().getImages().isEmpty()) {
			List<ProductImages> productImages = ModelMapperUtils.mapAll(productDTO.getProductDetails().getImages(),
					ProductImages.class);
			// TO-DO delete all images
			return productImages.stream().collect(Collectors.toSet());

		}
		return Collections.emptySet();
	}

}
