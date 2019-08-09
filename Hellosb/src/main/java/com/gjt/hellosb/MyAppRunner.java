package com.gjt.hellosb;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@SpringBootApplication
@Component
@Order(1)
public class MyAppRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        args.getOptionNames().forEach(opt->
        System.out.println("MyAppRunner ==="+opt+"="+args.getOptionValues(opt)));
    }

    public static void main(String[] args) {
        SpringApplication.run(MyAppRunner.class,args);
    }
}
