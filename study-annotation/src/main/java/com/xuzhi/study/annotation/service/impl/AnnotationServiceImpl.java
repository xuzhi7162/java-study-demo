package com.xuzhi.study.annotation.service.impl;

import com.xuzhi.study.annotation.annotation.TestAnnotation;
import com.xuzhi.study.annotation.service.AnnotationService;
import org.springframework.stereotype.Service;

@Service
public class AnnotationServiceImpl implements AnnotationService {


    @Override
    @TestAnnotation(name = "你好", type = "注解")
    public String test() {
        return "这是方法";
    }
}
