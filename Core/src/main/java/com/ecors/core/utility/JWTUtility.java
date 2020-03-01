package com.ecors.core.utility;

import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Jwts;

public class JWTUtility {

	public static String getUserId(HttpServletRequest request, String key, String header, String prefix) {
		String authorizationToken = request.getHeader(header);
		String token = authorizationToken.replace(prefix, "").trim();
		String userID = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
		return userID;
	}

}
