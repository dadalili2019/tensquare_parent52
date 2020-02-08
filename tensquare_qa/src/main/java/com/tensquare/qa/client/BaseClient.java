package com.tensquare.qa.client;

import com.tensquare.qa.client.Impl.BaseClientImpl;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author caoqian
 * @ClassName BaseClient   服务调用客户端接口
 * @Date 2020/2/5 12:48
 * @Version 1.0
 */
@FeignClient(value = "tensquare_base", fallback = BaseClientImpl.class)//如果服务器出错，找熔断器处理类
public interface BaseClient {

    @RequestMapping(value = "/label/{labelId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String labelId);
}
