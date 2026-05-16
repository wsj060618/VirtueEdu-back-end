package org.perswsj.utils;

import io.jsonwebtoken.*;

import java.util.Base64;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    // 固定密钥
    private static final String SECRET_KEY = Base64.getEncoder().encodeToString("perswsj".getBytes());
    // 12小时 过期时间
    private static final long EXPIRE_TIME = 43200000;

    /**
     * 生成JWT
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .compact();
    }

    /**
     * 解析JWT
     * 解析失败主动抛自定义异常，交给全局异常处理
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}