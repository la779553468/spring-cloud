package com.jwebidai.service.inter;

import java.util.Map;

/**
 * Created by wjh on 2018/3/8.
 */
public interface ISmsService {
    Map<String,Object> sendSms(String phone, String randNum, String baseSmsPubcode, Integer consumerFlag);
}
