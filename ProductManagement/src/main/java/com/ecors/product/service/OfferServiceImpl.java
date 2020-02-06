package com.ecors.product.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import com.ecors.core.dto.ServiceResponse;
import com.ecors.core.utility.ModelMapperUtils;
import com.ecors.product.DTO.OfferDTO;
import com.ecors.product.DTO.OfferZoneDTO;
import com.ecors.product.DTO.SubCategoryDTO;
import com.ecors.product.entity.Offer;
import com.ecors.product.entity.SubCategory;
import com.ecors.product.repository.OfferRepository;

@Service
public class OfferServiceImpl implements OfferService {

	private final static boolean ACTIVE = true;

	@Autowired
	private OfferRepository offerRepository;

	@Autowired
	private SubCategoryService subCategoryService;

	@Override
	public Optional<OfferZoneDTO> getByOfferName(String offerName, boolean active) {

		ServiceResponse<Offer, OfferDTO> offer = getOffer(offerName, active);
		OfferZoneDTO response = new OfferZoneDTO(offer.getDTO());
		response.setSubCategories(subCategoryService.getAllSubCateogry(offer.getEntity(), active).get());
		return Optional.ofNullable(response);
	}

	@Override
	public Optional<List<OfferDTO>> getAll(boolean active) {
		Optional<List<Offer>> offer = offerRepository.findByActive(true);

		if (offer.isPresent()) {
			return ModelMapperUtils.mapAll(offer.get(), OfferDTO.class);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No active offers available");
		}

	}

	protected ServiceResponse<Offer, OfferDTO> getOffer(String offerName, boolean active) {
		Optional<Offer> offer = offerRepository.findByActiveAndOfferName(active, offerName);
		Assert.isTrue(offer.isPresent(), "Invalid offername");
		ServiceResponse<Offer, OfferDTO> response = new ServiceResponse<>();
		response.setEntity(offer.get());
		response.setDTO(ModelMapperUtils.map(offer.get(), OfferDTO.class));
		return response;
	}
}
