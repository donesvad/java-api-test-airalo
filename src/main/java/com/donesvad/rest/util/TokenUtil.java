package com.donesvad.rest.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.time.Instant;

public class TokenUtil {
  public static boolean isTokenValid(String token) {
    DecodedJWT decodedJWT = JWT.decode(token);
    Instant expiration = decodedJWT.getExpiresAt().toInstant();
    Instant now = Instant.now();

    return expiration.isAfter(now);
  }
}
