package org.perswsj;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    /**
     * 测试生成JWT令牌
     */
    @Test
    public void testGenerateJwt() {
        Map<String,Object> map = new HashMap<>();
        map.put("username", "perswsj");
        map.put("password", "123456");
        map.put("role", "admin");
        String jwt = Jwts.builder()
                    .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString("perswsj".getBytes())) //指定签名算法和密钥
                    .addClaims(map) //添加自定义信息
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60)) //设置过期时间为1分钟
                    .compact(); //生成JWT令牌
        System.out.println(jwt);
    }

    /**
     * 测试解析JWT令牌
     */
    @Test
    public void testResolveJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjEyMzQ1NiIsInJvbGUiOiJhZG1pbiIsInVzZXJuYW1lIjoicGVyc3dzaiIsImV4cCI6MTc3NzIxMzE3Nn0.rlZVteHDbQJRPudP263Bu_OvEUJsI4gC-5ULVhILn5w";
        Claims claims = Jwts.parser()
                        .setSigningKey(Base64.getEncoder().encodeToString("perswsj".getBytes())) // 指定密钥
                        .parseClaimsJws(token) // 解析令牌
                        .getBody(); // 获取自定义信息
        System.out.println(claims);
    }
}
