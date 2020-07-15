package com.xuzhi.study.redis.test;

import com.xuzhi.study.redis.util.SerializeHelper;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<Serializable, Serializable> redisTemplate;

    @Test
    public void setnx()
    {
        Long    deadline = System.currentTimeMillis();
        boolean flag =  redisTemplate.execute((RedisConnection conn) -> {
            byte[] k = redisTemplate.getStringSerializer().serialize("test");
            byte[] v = SerializeHelper.serialize(deadline);
            return conn.setNX(k, v);
        });
        System.out.println(flag);
    }

    @Data
    private class User implements Serializable{
        private String name;

        public User() {
            this.name = "我是你爸爸";
        }
    }
}
