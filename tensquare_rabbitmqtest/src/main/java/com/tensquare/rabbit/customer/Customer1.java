package com.tensquare.rabbit.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author caoqian
 * @ClassName Customer1
 * @Date 2020/2/3 12:39
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "itcast")
public class Customer1 {

    /**
     * 直连模式
     * @param msg
     */
    @RabbitHandler
    public void getMsg(String msg){
        System.out.println("直接模式消费信息:"+msg);
    }
}
