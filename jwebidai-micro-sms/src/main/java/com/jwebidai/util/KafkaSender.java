package com.jwebidai.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by wjh on 2018/3/9.
 */
@Component("kafkaSender2")
public class KafkaSender {
    private static Logger logger = LoggerFactory.getLogger(KafkaSender.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendMessage(String topicName, String jsonData) {
        logger.info("向kafka推送数据:[{}]", jsonData);
        try {
            kafkaTemplate.send(topicName, jsonData);
        } catch (Exception e) {
            logger.error("发送数据出错！！！{}{}", topicName, jsonData);
            logger.error("发送数据出错=====>", e);
        }
    }
}
