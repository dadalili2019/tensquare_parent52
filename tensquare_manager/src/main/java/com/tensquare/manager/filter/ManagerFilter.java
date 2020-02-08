package com.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author caoqian
 * @ClassName ManagerFilter 网关过滤器
 * @Date 2020/2/7 11:15
 * @Version 1.0
 */
@Component
public class ManagerFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 过滤器类型
     *
     * @return pre:表示请求之前执行 post:后执行
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 多过滤器执行顺序
     *
     * @return 表示执行顺序 数字越小先执行
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 当前过滤器是否开启，true表示开始，false表示不开启
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器内执行的操作
     *
     * @return 任何object的值表示都表示继续执行
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        //request域
        HttpServletRequest request = requestContext.getRequest();
        if (request.getMethod().equals("OPTIONS")) {
            return null;
        }
        if (request.getRequestURI().indexOf("login") > 0) {
            return null;
        }
        //得到头信息
        String header = request.getHeader("Authorization");
        if (header != null && !"".equals(header)) {
            if (header.startsWith("Bearer ")) {
                String token = header.substring(7);
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if (roles.equals("admin")) {
                        //将头信息进行转发
                        requestContext.addZuulResponseHeader("Authorization", header);
                        return null;
                    }
                } catch (Exception e) {
                    requestContext.setSendZuulResponse(false);
                }
            }
        }
        requestContext.setSendZuulResponse(false);//终止运行
        requestContext.setResponseStatusCode(403);//权限不足
        return null;
    }
}
