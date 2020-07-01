package com.board.backend.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;
import java.util.Date;


public class JwtTokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final long expiration_time = 86400000;

    private Key key;

    public JwtTokenUtil( String secretKey) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String createToken(String userId,String userName, String role) {
        JwtBuilder builder = Jwts.builder()
                .claim("userId", userId)
                .claim("userName", userName)
                .claim("role", role);

        return builder
                .signWith( SignatureAlgorithm.HS256, key)
                .setExpiration(expireTime())
                .compact();

    }

    public Date expireTime() {
        Date expireTime = new Date();
        expireTime.setTime(expireTime.getTime() + expiration_time);
        return expireTime;
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

    }

    public boolean isUsable(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();

            return true;

        } catch (Exception e) {
            throw new RuntimeException("권한이 유효하지 않습니다.");
        }
    }
}
