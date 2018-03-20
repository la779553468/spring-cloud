package com.jwebidai.util.kafka;

/**
 * Created by demondevil on 16/3/28.
 */
public class KafkaSender {
    private static KafkaTemplate kafkaTemplate = null;

    private KafkaSender(){

    }

    public static void send(String topic,String msg){
        if ( kafkaTemplate == null ){
            throw new RuntimeException("KafkaSender's kafkaTemplate is null...");
        }
        kafkaTemplate.send(topic,msg);
    }

    public static void setKafkaTemplate(KafkaTemplate template){
        kafkaTemplate = template;
    }
}
