package com.jwebidai.util.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.util.Properties;

/**
 * Created by lms on 15-8-31.
 */
public class KafkaFactory extends BasePooledObjectFactory<Producer<String, String>> {
    private ProducerConfig config;
    public KafkaFactory(Properties props){
        this.config = new ProducerConfig(props);
    }



    /**
     * 创建一个连接对象
     * @return
     * @throws Exception
     */
    @Override
    public Producer<String, String> create() throws Exception {
       // System.out.println("创建链接对象");
        return new Producer<String, String>(this.config);
    }

    /**
     * 将对象转换为连接池对象
     * @param producer
     * @return
     */
    @Override
    public PooledObject<Producer<String, String>> wrap(Producer<String, String> producer) {
      //  System.out.println("将建链接对象转换为池对象");
        return new DefaultPooledObject<Producer<String, String>>(producer);
    }


    @Override
    public void destroyObject(PooledObject<Producer<String, String>> p) throws Exception {
        //System.out.println("通知啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊");
        p.getObject().close();
    }
}
