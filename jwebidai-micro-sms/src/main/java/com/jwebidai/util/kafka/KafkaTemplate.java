package com.jwebidai.util.kafka;

import kafka.common.KafkaException;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * kafka池对象
 * Created by lms on 15-8-31.
 */
public class KafkaTemplate extends Pool<Producer<String,String>>{
    Logger logger = LoggerFactory.getLogger(KafkaTemplate.class);

    public KafkaTemplate(GenericObjectPoolConfig poolConfig, PooledObjectFactory<Producer<String, String>> factory) {
        super(poolConfig, factory);
    }



    public void send(String topic,String msg){
        Producer<String, String> producer = null;

        boolean success = true;
        try{
            producer = this.getResource();
            producer.send(new KeyedMessage<String, String>(topic,msg));
            logger.debug("topic:"+topic+",msg:"+msg);
        }catch (KafkaException e){
            success=false;
            if (producer!=null){
                this.returnBrokenResourceObject(producer);
            }
            logger.error("KafkaTemplate send error. topic="+topic+",msg="+msg,e);
            throw new KafkaException(e);
        }finally {
            if (success&&producer!=null){
                this.returnResourceObject(producer);
            }

        }
    }



}
