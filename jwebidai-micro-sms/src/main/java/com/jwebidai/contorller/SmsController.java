package com.jwebidai.contorller;

import com.jwebidai.requestBean.SmsRequest;
import com.jwebidai.service.inter.ISmsService;
import com.jwebidai.util.JsonUtil;
import com.jwebidai.util.RedisUtil;
import com.jwebidai.util.SmsUtil;
import com.jwebidai.util.ValidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 作用：短信服务提供
 * @author wjh
 */
@RestController
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    private ISmsService smsService;

    private static String baseSmsPubcode = "idai";

    /**
     * 注：@GetMapping("/{id}")是spring 4.3的新注解等价于：
     *
     * @param smsRequest 短信请求json串
     * @return user信息
     * @RequestMapping(value = "/id", method = RequestMethod.GET)
     * 类似的注解还有@PostMapping等等
     * 通用短信发送接口
     */
    @PostMapping("/sendBaseSms")
    @ResponseBody
    public String findById(@RequestBody SmsRequest smsRequest) {
        Map<String, Object> map = new HashMap<>();

        //手机号格式校验
        String phone = smsRequest.getPhone();
        if(!ValidUtil.isPhone(phone)) {
            map.put("code", "error");
            map.put("msg", "手机号格式错误！");
            return JsonUtil.toJson(map);
        }

        //45秒以内一个手机号只能发送一次验证码
        String reSendInterval = RedisUtil.use().get(phone+"_interval");//重发间隔
        if(reSendInterval != null) { //判断 仍然在时间间隔内
            map.put("code", 200);
            return JsonUtil.toJson(map);
        }

        //根据比率，选择发送通道
        int consumerFlag = SmsUtil.getSmsPos();
        //生成验证码
        String randNum = SmsUtil.getRandVry();

        //判断 手机号是否成功注册过
        String _registOkConsumer = RedisUtil.use().get(phone+"_registOkConsumer");
        if(_registOkConsumer != null) {
            consumerFlag = Integer.valueOf(_registOkConsumer);
        }

        // 判断是否需要切换通道
        String _consumer = RedisUtil.use().get(phone+"_consumer");
        if(_consumer != null) {
            consumerFlag = Integer.valueOf(_consumer)==1?2:1;
        }

        // 发送短信
        map = smsService.sendSms(phone, randNum, baseSmsPubcode, consumerFlag);
        //异步操作 保存发送日志
        return JsonUtil.toJson(map);
    }

}