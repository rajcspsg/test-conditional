package com.as.demo.app;

import com.as.demo.config.AerospikeConfig;
import com.as.demo.entity.User;
import org.springframework.context.ApplicationContext;
import com.as.demo.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AerospikeApp {

    public static void main(String []args) {
        ApplicationContext appCtx = new AnnotationConfigApplicationContext(AerospikeConfig.class);
       // ApplicationContext appCtx =new ClassPathXmlApplicationContext("AerospikeConfig.xml");
        UserService us = appCtx.getBean(UserService.class);
        us.saveUser(new User("1", "myname"));
    }
}
