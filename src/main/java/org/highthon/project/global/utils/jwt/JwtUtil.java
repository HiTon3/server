package org.highthon.project.global.utils.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

  // 만료되었는지 확인하는 메서드
  public static boolean isExpired(String token, String secretKey) {
    return Jwts.parserBuilder()
      .setSigningKey(secretKey)
      .build()
      .parseClaimsJws(token)
      .getBody()
      .getExpiration()
      .before(new Date()); // token이 expired 된 것이 지금보다 전이면 expired 된 것이다
  }

  public static String createToken(String email, String secret, Long exprTime) {
    Claims claims = Jwts.claims();
    claims.put("email", email);

    return Jwts.builder()
      .setClaims(claims)
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + exprTime))
      .signWith(SignatureAlgorithm.HS256, secret)
      .compact();
  }
}
