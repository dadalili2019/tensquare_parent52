package com.tensequare.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @Author caoqian
 * @ClassName CreateJwt
 * @Date 2020/2/4 12:19
 * @Version 1.0
 */
public class CreateJwt {
    public static void main(String[] args) {
        JwtBuilder jwtBuilder=Jwts.builder()
                .setId("666")
                .setSubject("小马")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"itcast")
                .setExpiration(new Date(new Date().getTime()+60000));
        System.out.println(jwtBuilder.compact());
    }
}
