package com.tensquare.base.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author caoqian
 * @ClassName BaseExceptionHandler
 * @Date 2020/1/16 21:00
 * @Version 1.0
 *
 * 公共异常处理
 */
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result exception(Exception e){
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR,e.getMessage());
    }
}
