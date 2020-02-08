package com.tensquare.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author caoqian
 * @ClassName WebFilter  前台网关
 * @Date 2020/2/7 18:02
 * @Version 1.0
 */
@Component
public class WebFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //得到request上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //得到request域
        HttpServletRequest request = currentContext.getRequest();
        //得到头信息
        String header = request.getHeader("Authorization");
        //判断是否有头信息
        if (header != null && !"".equals(header)) {
            //把头信息继续向下传
            currentContext.addZuulResponseHeader("Authorization", header);
        }
        return null;
    }
}
