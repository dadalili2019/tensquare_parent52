package com.tensquare.friend.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author caoqian
 * @ClassName JwtInterceptor  拦截器配置类
 * @Date 2020/2/5 1:13
 * @Version 1.0
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //无论如何都放行，具体能不能操作在具体情况中做判断
        //拦截器只是负责把请求头中包含token的令牌进行解析验证。
        String header = request.getHeader("Authorization");
        if (header != null && !"".equals(header)) {
            //如果有包含Authorization头信息，就对其进行解析
            if (header.startsWith("Bearer ")) {
                //得到token
                String token = header.substring(7);
                //对令牌进行验证
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if (roles != null && roles.equals("admin")) {
                        request.setAttribute("claims_admin", token);
                    }
                    if (roles != null && roles.equals("user")) {
                        request.setAttribute("claims_user", token);
                    }
                } catch (Exception e) {
                    throw new Exception("令牌不正确");
                }
            }
        }

        return true;
    }
}
