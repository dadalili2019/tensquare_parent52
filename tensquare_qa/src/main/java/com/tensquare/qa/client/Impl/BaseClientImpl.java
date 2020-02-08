package com.tensquare.qa.client.Impl;

import com.tensquare.qa.client.BaseClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 * @Author caoqian
 * @ClassName BaseClientImpl   熔断器处理类
 * @Date 2020/2/7 9:38
 * @Version 1.0
 */
@Component
public class BaseClientImpl implements BaseClient {

    /**
     * 如想做特殊处理，则直接在方法中自定义处理。
     * 如出错，直接走向此分支（方法）
     *
     * @param labelId
     * @return
     */
    @Override
    public Result findById(String labelId) {
        return new Result(false, StatusCode.ERROR, "熔断器触发了");
    }
}
