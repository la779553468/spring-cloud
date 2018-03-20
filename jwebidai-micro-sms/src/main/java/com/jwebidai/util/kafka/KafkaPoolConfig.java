package com.jwebidai.util.kafka;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * 数据连接池参数配置
 * Created by lms on 15-8-31.
 */
public class KafkaPoolConfig extends GenericObjectPoolConfig {

    public KafkaPoolConfig() {
        setTestWhileIdle(true);                    //如果为true，表示有一个idle object evitor线程对idle object进行扫描，如果validate失败，此object会被从pool中drop掉；这一项只有在timeBetweenEvictionRunsMillis大于0时才有
        setMinEvictableIdleTimeMillis(60000);     //表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义；
        setTimeBetweenEvictionRunsMillis(30000);  //表示idle object evitor两次扫描之间要sleep的毫秒数
        setNumTestsPerEvictionRun(-1);              //表示idle object evitor每次扫描的最多的对象数,-1表示所有；

    }
}
