package com.jwebidai.util.kafka;

import com.jwebidai.config.KafkaConfig;
import kafka.common.KafkaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;

/**
 * Created by demondevil on 16/3/28.
 */
@Component
public class KafkaPlugin {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private int maxTotal = 100;
    private int maxIdle = 20;
    private int minIdle = 10;
    private int maxWaitMillis = 20000;
    private boolean testOnBorrow = true;

    private String metadataBrokerList;
    private String serializerClass;

    private KafkaTemplate kafkaTemplate;

    @Autowired
    private KafkaConfig kafkaConfig;

    // 注解含义： 构造方法之后执行
    @PostConstruct
    public void start() {
        logger.info("start KafkaPlugin ... ");

        try {
            loadProperties();

            // KafkaPoolConfig
            KafkaPoolConfig kafkaPoolConfig = new KafkaPoolConfig();
            kafkaPoolConfig.setMaxTotal(this.maxTotal);
            kafkaPoolConfig.setMaxIdle(this.maxIdle);
            kafkaPoolConfig.setMinIdle(this.minIdle);
            kafkaPoolConfig.setMaxWaitMillis(this.maxWaitMillis);
            kafkaPoolConfig.setTestOnBorrow(this.testOnBorrow);

            // KafkaFactory
            Properties properties = new Properties();
            properties.setProperty("metadata.broker.list",this.metadataBrokerList);
            properties.setProperty("serializer.class",this.serializerClass);
            KafkaFactory kafkaFactory = new KafkaFactory(properties);

            // KafkaTemplate
            kafkaTemplate = new KafkaTemplate(kafkaPoolConfig,kafkaFactory);
            kafkaTemplate.getResource();
            KafkaSender.setKafkaTemplate(kafkaTemplate);
        } catch (Exception e) {
            throw new KafkaException("KafkaPlugin start error...",e);
        }

    }

    // 加载配置
    private void loadProperties() {
        this.maxTotal =  kafkaConfig.getMaxTotal();
        this.minIdle =  kafkaConfig.getMinIdle();
        this.maxIdle = kafkaConfig.getMaxIdle();
        this.maxWaitMillis = kafkaConfig.getMaxWaitMillis();
        this.testOnBorrow = kafkaConfig.getTestOnBorrow();

        this.metadataBrokerList = kafkaConfig.getSyncProducerBrokers();
        this.serializerClass = kafkaConfig.getSyncProducerSerializer();
    }

    // 注解含义： bean销毁之前执行
    @PreDestroy
    public void stop() {
        try {
            kafkaTemplate.destroy();
            KafkaSender.setKafkaTemplate(null);
        } catch (Exception e) {
            throw new KafkaException("KafkaPlugin stop error...",e);
        }
    }
}
