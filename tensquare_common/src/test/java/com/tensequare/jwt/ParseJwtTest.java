package com.tensequare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

/**
 * @Author caoqian
 * @ClassName ParseJwtTest
 * @Date 2020/2/4 12:42
 * @Version 1.0
 */
public class ParseJwtTest {
    public static void main(String[] args) {
        Claims claims = Jwts.parser()
                .setSigningKey("itcast")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiLlsI_pqawiLCJpYXQiOjE1ODA3OTEzNTJ9.90I4y0O0s-oCo1lRmByWl6Yk9XQLFr3jQBM61CUJq0Y")
                .getBody();
        System.out.println("用户id:"+claims.getId());
        System.out.println("用户名:"+claims.getSubject());
        System.out.println("登录时间:"+ new SimpleDateFormat("yyyy-MM-dd").format(claims.getIssuedAt()));
        System.out.println("过期时间:"+ new SimpleDateFormat("yyyy-MM-dd").format(claims.getExpiration()));
    }
}
