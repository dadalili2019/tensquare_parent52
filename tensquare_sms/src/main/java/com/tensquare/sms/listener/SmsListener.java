package com.tensquare.sms.listener;

import com.aliyuncs.exceptions.ClientException;
import com.tensquare.sms.utils.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author caoqian
 * @ClassName SmsListener
 * @Date 2020/2/3 19:42
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {

    @Autowired
    private SmsUtil smsUtil;

    @Value("${aliyun.sms.template_code}")
    private String template_code;//模板编号

    @Value("${aliyun.sms.sign_name}")
    private String sign_name;//签名

    /**
     * 接收短信MQ
     * (消费者)
     *
     * @param map
     */
    @RabbitListener
    public void executeSms(Map<String, String> map) {
        String mobile = map.get("mobile");
        String checkcode = map.get("code");
        System.out.println("手机号：" + map.get("mobile"));
        System.out.println("验证码：" + map.get("code"));
        try {
            smsUtil.sendSms(mobile, template_code, sign_name, "{\"checkcode\":\"" + checkcode + "\"}");
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
