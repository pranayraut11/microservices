package com.ecors.core.utility;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ModelMapperUtils {
	public static ModelMapper mapper = null;
	public static ObjectMapper jsonMapper = null;

	static {
		mapper = new ModelMapper();
		jsonMapper = new ObjectMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
				.setPropertyCondition(Conditions.isNotNull());
		;
	}

	private ModelMapperUtils() {

	}

	public static <D, T> D map(T entity, Class<D> outClass) {
		return mapper.map(entity, outClass);

	}

	public static <D, T> List<D> mapAll(final List<T> entityList, Class<D> outCLass) {
		return entityList.stream().map(entity -> map(entity, outCLass)).collect(Collectors.toList());
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

	public static Map<String, String> mapJson(String json) throws JsonMappingException, JsonProcessingException {
		return jsonMapper.readValue(json, new TypeReference<Map<String, String>>() {
		});
	}

}
