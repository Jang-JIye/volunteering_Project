package com.team8.volunteerworkproject.jwt;

import com.team8.volunteerworkproject.common.RedisDao;
import com.team8.volunteerworkproject.enums.JwtEnum;
import com.team8.volunteerworkproject.enums.UserRoleEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

  private final RedisDao redisDAO;

  public static final String AUTHORIZATION_HEADER = "Authorization";
  public static final String REFRESH_HEADER = "Refresh";
  public static final String AUTHORIZATION_KEY = "auth";
  private static final String BEARER_PREFIX = "Bearer ";
  private static final long TOKEN_TIME = 60 * 60 * 1000L;
  private static final long REFRESH_TOKEN_TIME = 7 * 24 * 60 * 60 * 1000L;

  @Value("${jwt.secret.key}")
  private String secretKey;
  private Key key;
  private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

  @PostConstruct
  public void init() {
    byte[] bytes = Base64.getDecoder().decode(secretKey);
    key = Keys.hmacShaKeyFor(bytes);
  }

  // header 토큰을 가져오기
  public String resolveToken(HttpServletRequest request, String header) {
    String bearerToken = request.getHeader(header);
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
      return bearerToken.substring(7);
    }
    return null;
  }

  // 토큰 생성
  public String createToken(String username, UserRoleEnum role) {
    Date date = new Date();

    return BEARER_PREFIX +
        Jwts.builder()
            .setSubject(username)
            .claim(AUTHORIZATION_KEY, role) // "auth" : role
            .setExpiration(new Date(date.getTime() + TOKEN_TIME))
            .setIssuedAt(date)
            .signWith(key, signatureAlgorithm)
            .compact();
  }

  // 리프레쉬 토큰 생성
  public String createRefreshToken(String username, UserRoleEnum role) {
    Date date = new Date();

    return Jwts.builder()
        .setSubject(username)
        .claim(AUTHORIZATION_KEY, role)
        .setExpiration(new Date(date.getTime() + REFRESH_TOKEN_TIME))
        .setIssuedAt(date)
        .signWith(key, signatureAlgorithm)
        .compact();
  }

  public String getRefreshTokenFromRedis(String key) {
    return String.valueOf(redisDAO.getValues(key).orElse(""));
  }

  public String reissueAccessToken(String refreshToken) {
    Claims claims = this.getUserInfoFromToken(refreshToken);
    return this.createToken(claims.getSubject(),
        UserRoleEnum.valueOf(claims.get("auth").toString()));
  }

  public String getRedisKey(String token) {
    return token.substring(token.length() - 8);
  }

  public String getUserIdFromExpiredToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    } catch (ExpiredJwtException e) {
      return e.getClaims().getSubject();
    }
    return null;
  }

  // 토큰 검증
  public JwtEnum validateToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
      return JwtEnum.ACCESS;
    } catch (ExpiredJwtException e) {
//            log.info("Expired JWT token, 만료된 JWT token 입니다.");
      return JwtEnum.EXPIRED;
    } catch (SecurityException | MalformedJwtException e) {
      log.info("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
    } catch (UnsupportedJwtException e) {
      log.info("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
    } catch (IllegalArgumentException e) {
      log.info("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
    }
    return JwtEnum.DENIED;
  }

  // 토큰에서 사용자 정보 가져오기
  public Claims getUserInfoFromToken(String token) {
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
  }
}