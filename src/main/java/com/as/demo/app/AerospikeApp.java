package com.as.demo.app;

import com.as.demo.config.AerospikeConfig;
import com.as.demo.entity.User;
import org.springframework.context.ApplicationContext;
import com.as.demo.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class AerospikeApp {

    public static void main(String []args) throws Exception {
        Properties props = new Properties();
        ClassLoader classLoader = AerospikeApp.class.getClassLoader();
        File file = new File(classLoader.getResource("local.properties").getFile());
        props.load(new FileReader(file));
       // System.out.println(File);
        System.out.println("props.size() " +props.size());
        props.forEach((k,v) -> {
            System.out.println("k = "+k.toString()+" v ="+v.toString());
            System.setProperty(k.toString(),v.toString());
        });
        ApplicationContext appCtx = new AnnotationConfigApplicationContext(AerospikeConfig.class);
       // ApplicationContext appCtx =new ClassPathXmlApplicationContext("AerospikeConfig.xml");
        UserService us = appCtx.getBean(UserService.class);
        us.saveUser(new User("1", "myname"));
    }
}
