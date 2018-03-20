package com.jwebidai.service;

import com.jwebidai.bean.SmsBean;
import com.jwebidai.config.SmsConfig;
import com.jwebidai.service.inter.ISmsService;
import com.jwebidai.util.JsonUtil;
import com.jwebidai.util.RedisUtil;
import com.jwebidai.util.kafka.KafkaSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wjh on 2018/3/8.
 */
@Service("smsService")
public class SmsService implements ISmsService {

    private static Logger logger = LoggerFactory.getLogger(SmsService.class);

    private String whitePhones = "15697363907|17673055915|18824395070|15118000384";

    @Autowired
    private SmsConfig smsConfig;

    public Map<String,Object> sendSms(String phone, String randNum, String pubcode, Integer consumerFlag) {
        Map<String,Object> map = new HashMap<>();

//        String cont = smsConfig.getContent().replace("[]", randNum);
        String cont = "你的验证码为[],nice!".replace("[]", randNum);
        System.out.println(cont);
        SmsBean smsBean = new SmsBean();
        smsBean.setPhones(new String[]{ phone });
        smsBean.setPubcode(pubcode);
        smsBean.setContent(cont);
        smsBean.setVry(randNum);

        String recountTemp = RedisUtil.use().get(phone+"_recount");//重发次数
        if(recountTemp == null) {
            recountTemp = "0";
        }
        Integer recount = Integer.valueOf(recountTemp);
        recount ++;
        int limit = 5;
        if(whitePhones.contains(phone)) {
            limit = 100;
        }
        if(recount > limit) {
            map.put("code", "recount_error");
            map.put("msg", "验证码发送超过次数，请24小时后重试！");
            return map;
        }
        try {
            if(consumerFlag == 2) { // 跟据权重计算加入融云消费端
                smsBean.setPubcode("rongy");
                String msg = JsonUtil.toJson(smsBean);
                logger.info("send sms_data to idai_kafka,msg="+msg);
                KafkaSender.send(smsConfig.getTopic2(),msg);
            }else { // 跟据权重计算加入三三得玖消费端
                String msg = JsonUtil.toJson(smsBean);
                logger.info("send sms_data to idai_kafka,msg="+msg);
                KafkaSender.send(smsConfig.getTopic(),msg);
            }

        } catch (Exception e) {
            logger.warn("消息发送至kafka出错：" + e);
            map.put("code", "recount_error");
            map.put("msg", "系统繁忙，请稍后重试！");
            return map;
        }

        RedisUtil.use().setExpireSeconds(phone+"_recount",recount.toString(),3600*24);
        RedisUtil.use().setExpireMinute(phone, randNum,10);
        RedisUtil.use().setExpireSeconds(phone+"_interval", "1", 45);
        RedisUtil.use().setExpireMinute(phone+"_consumer", consumerFlag.toString(), 2);
        map.put("code", "ok");
        map.put("msg", null);
        return map;
    }
}
