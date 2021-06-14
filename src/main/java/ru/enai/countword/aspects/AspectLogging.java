package ru.enai.countword.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class AspectLogging {
    @AfterThrowing( pointcut = "execution(* ru.enai.countword..*.*(..))",
            throwing = "e")
    public void afterThrowingAdvice(JoinPoint jp, Throwable e){
        log.error("Method Signature: "  + jp.getSignature()+". Exception: "+e);
        System.exit(100);
    }
}
