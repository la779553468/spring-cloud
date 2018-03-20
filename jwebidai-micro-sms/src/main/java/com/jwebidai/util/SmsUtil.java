package com.jwebidai.util;

import org.apache.commons.lang.StringUtils;

import java.util.Random;

/**
 * Created by wjh on 2018/3/8.
 */
public class SmsUtil {

    private static String CONSUMER_HZ = "consumer_hz";

    public static int getSmsPos() {
        String hzrate = RedisUtil.use().get(CONSUMER_HZ);
        Random random = new Random();
        int res = random.nextInt(100)+1;
        if(StringUtils.isNotBlank(hzrate) && res > Integer.valueOf(hzrate)) { //融云消费
            return 2;
        }else { //hz消费
            return 1;
        }
    }

    private static final String CODE_LIST = "1234567890";

    //随机生成4位验证码
    public static String getRandVry() {
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            Random random = new Random();
            int rand = random.nextInt(CODE_LIST.length());
            String strRand = CODE_LIST.substring(rand, rand + 1);
            sRand += strRand;
        }
        return sRand;
    }
}
