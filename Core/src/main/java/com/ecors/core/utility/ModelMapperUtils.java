package com.ecors.core.utility;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ModelMapperUtils {
	public static ModelMapper mapper = null;
	static {
		mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
				.setPropertyCondition(Conditions.isNotNull());
		;
	}

	private ModelMapperUtils() {

	}

	public static <D, T> D map(T entity, Class<D> outClass) {
		return mapper.map(entity, outClass);

	}

	public static <D, T> Optional<List<D>> mapAll(final Collection<T> entityList, Class<D> outCLass) {
		return Optional
				.ofNullable(entityList.stream().map(entity -> map(entity, outCLass)).collect(Collectors.toList()));
	}

	public static <D, T> List<D> mapAll(final Optional<Collection<T>> entityList, Class<D> outCLass) {
		if (entityList.isPresent()) {
			return entityList.get().stream().map(entity -> map(entity, outCLass)).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	public static <S, D> D map(final S source, D destination) {
		mapper.map(source, destination);
		return destination;
	}
}
