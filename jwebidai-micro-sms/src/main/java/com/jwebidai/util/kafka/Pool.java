package com.jwebidai.util.kafka;

import kafka.common.KafkaException;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * 创建公用的池
 * Created by lms on 15-8-31.
 */
public class Pool<T>{

    private final GenericObjectPool internalPool;

    public Pool(final GenericObjectPoolConfig poolConfig, PooledObjectFactory<T> factory) {
        this.internalPool = new GenericObjectPool(factory, poolConfig);
    }
    /**
     * 获取链接对象
     * @return
     */
    @SuppressWarnings("unchecked")
    public T getResource() {
        try {
          //  System.out.println("从连接池中获取对象");
            return (T) internalPool.borrowObject();
        } catch (Exception e) {
            throw new KafkaException("从连接池中获取连接对象失败", e);
        }
    }

    /**
     * 将链接对象放到连接池中
     * @param resource
     */
    public void returnResourceObject(final Object resource) {
        try {
           // System.out.println("将链接对象放到连接池中");
            internalPool.returnObject(resource);
        } catch (Exception e) {
            throw new KafkaException("将链接对象放到连接池失败", e);
        }
    }

    /**
     * 将连接池中的对象销毁，一般用于处理catch exception的情况
     * @param resource
     */
    public void returnBrokenResource(final T resource) {
        returnBrokenResourceObject(resource);
    }

    /**
     * 将链接对象放到连接池中
     * @param resource
     */
    public void returnResource(final T resource) {
        returnResourceObject(resource);
    }


    /**
     * 销毁连接池
     */
    public void destroy() {
        try {
            internalPool.close();
        } catch (Exception e) {
            throw new KafkaException("销毁连接池失败", e);
        }
    }


    /**
     * 将连接池中的对象销毁，一般用于处理catch exception的情况
     * @param resource
     */
    protected void returnBrokenResourceObject(final Object resource) {
        try {
            //失效
            internalPool.invalidateObject(resource);
        } catch (Exception e) {
            throw new KafkaException("销毁链接对象失败", e);
        }
    }
}
