package com.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class JwtUtils {
    public static String generateToken(Map<String,Object> dataMap){
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "123456")
                .addClaims(dataMap).setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();//添加自定义信息

        return jwt;
    }

    public static Map<String,Object> parseToken(String token){
        Claims claims = Jwts.parser().setSigningKey("123456").parseClaimsJws(token).getBody();
        log.info("解析得到 claims 内容为: {}", claims); // 看看有没有 id
        return claims;
    }
}
