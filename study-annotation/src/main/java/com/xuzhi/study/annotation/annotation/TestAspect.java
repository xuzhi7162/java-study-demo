package com.xuzhi.study.annotation.annotation;

import lombok.val;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {

    @Pointcut("@annotation(com.xuzhi.study.annotation.annotation.TestAnnotation)")
    public void pointCut() {

    }

    @AfterReturning(returning = "object", value = "pointCut()")
    public Object around(JoinPoint joinPoint, Object object) {
        try {
            return object;
        }finally {
            oper(joinPoint);
        }
    }

    public void oper(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        TestAnnotation testAnnotation = signature.getMethod().getAnnotation(TestAnnotation.class);
        System.out.println(testAnnotation.name());
        System.out.println(testAnnotation.type());
        val demo = joinPoint.getArgs();
        System.out.println(demo);
    }


}
