package com.liangjinhai.redisdemo;


import com.fasterxml.jackson.core.type.TypeReference;
import com.liangjinhai.redisdemo.redis.service.RedisService;
import com.liangjinhai.redisdemo.util.JsonUtil;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDemoApplicationTests {
    private JSONObject json = new JSONObject();

    @Autowired
    private RedisService redisService;

    @Test
    public void contextLoads() throws Exception {
    }

    /**
     * 插入字符串
     */
    @Test
    public void setString() {
        redisService.set("redis_string_test", "springboot redis test");
    }

    /**
     * 获取字符串
     */
    @Test
    public void getString() {
        String result = redisService.get("redis_string_test");
        System.out.println(result);
    }

    /**
     * 插入对象
     */
    @Test
    public void setObject() {
        Person person = new Person("person", "male");
        redisService.set("redis_obj_test", JsonUtil.object2Json(person));
    }

    /**
     * 获取对象
     */
    @Test
    public void getObject() {
        String result = redisService.get("redis_obj_test");
        Person person = JsonUtil.json2Object(result, Person.class);
        System.out.println(JsonUtil.object2Json(person));
    }

    /**
     * 插入对象List
     */
    @Test
    public void setList() {
        Person person1 = new Person("person1", "male");
        Person person2 = new Person("person2", "female");
        Person person3 = new Person("person3", "male");
        List<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(person2);
        list.add(person3);
        redisService.set("redis_list_test", JsonUtil.object2Json(list));
    }

    /**
     * 获取list
     */
    @Test
    public void getList() {
        String result = redisService.get("redis_list_test");
        List<String> list = JsonUtil.json2List(result, new TypeReference<List<String>>() {
        });
        System.out.println(list);
    }

    @Test
    public void remove() {
        redisService.remove("redis_test");
    }
}

class Person {
    private String name;
    private String sex;

    public Person() {
    }

    public Person(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}


