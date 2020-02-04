package com.ecors.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecors.product.entity.Offer;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Integer> {

	Optional<List<Offer>> findByActive(boolean isActive);
	
	Optional<Offer> findByActiveAndOfferName(boolean isActive,String offerName);

}
