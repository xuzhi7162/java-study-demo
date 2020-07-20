package com.xuzhi.study.test;


import com.xuzhi.study.annotation.AnnotationApplication;
import com.xuzhi.study.annotation.service.AnnotationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AnnotationApplication.class)
public class AnnotationTestDemo {


    @Autowired
    private AnnotationService annotationService;

    @Test
    public void t1() {
        annotationService.test();
    }
}
