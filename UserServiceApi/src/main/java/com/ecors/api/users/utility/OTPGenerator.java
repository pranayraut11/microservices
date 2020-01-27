package com.ecors.api.users.utility;

import org.apache.commons.lang.math.RandomUtils;

public class OTPGenerator {

	private static final int LIMIT = 9999;

	public static int generate() {

		return RandomUtils.nextInt(LIMIT);

	}

	public static int generate(int limit) {

		return RandomUtils.nextInt(limit);

	}
	
	public static String generateAsString() {

		return Integer.toString(RandomUtils.nextInt(LIMIT));

	}

}
