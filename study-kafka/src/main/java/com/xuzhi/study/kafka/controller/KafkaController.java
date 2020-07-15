package com.xuzhi.study.kafka.controller;

import com.xuzhi.study.common.util.JsonFormat;
import com.xuzhi.study.kafka.config.KafkaService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private JsonFormat jsonFormat;

    public static int age = 2;

    @GetMapping("/send")
    public String send() {
        User user = new User();
        user.setAge(KafkaController.age ++);
        user.setName("xuzhi");
        String json = jsonFormat.toJson(user);
        kafkaService.send(json, "study-kafka");
        return "success";
    }

    @Data
    private class User {
        private String name;
        private int age;
    }
}
