package com.ecors.product.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecors.product.DTO.OfferDTO;
import com.ecors.product.entity.Offer;
import com.ecors.product.repository.OfferRepository;

@Service
public class OfferServiceImpl implements OfferService {

	private final static boolean ACTIVE = true;

	@Autowired
	private OfferRepository offerRepository;

	@Override
	public Optional<List<OfferDTO>> listOfferByOfferName(String offerName) {

		Optional<Offer> offerListOptional = offerRepository.findByActiveAndOfferName(ACTIVE, offerName);

		ModelMapper mapper = new ModelMapper();

		
		offerListOptional.get().getSubCategories().forEach(subCategory -> {

		});
		return null;
	}

}
