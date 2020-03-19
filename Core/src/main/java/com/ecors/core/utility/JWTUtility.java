package com.ecors.core.utility;

import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Jwts;

public class JWTUtility {

	private static final String HEADER = "Authorization";
	private static final String PREFIX = "Bearer";

	public static String extractUUIDFromToken(String token, String key) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
	}

	public static String extractUUIDFromHttpRequest(HttpServletRequest request, String header, String prefix) {
		String authorizationHeader = request.getHeader(header);
		return authorizationHeader.replace(prefix, "").trim();
	}

	public static String extractUUIDFromHttpRequest(HttpServletRequest request) {
		String authorizationHeader = request.getHeader(HEADER);
		return authorizationHeader.replace(PREFIX, "").trim();
	}

}
