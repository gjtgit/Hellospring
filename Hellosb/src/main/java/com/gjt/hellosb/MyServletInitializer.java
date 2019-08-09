package com.gjt.hellosb;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*
使用外部servlet 容器步骤
1.pom中packaging 成 war,自行创建webapp/WEB-INF等目录
2.starter-web 加上 scope=provided
3.实现SpringBootServletInitializer,重写configure方法,传入springboot的主程序
注意
spring-boot项目使用的jdk版本要和tomcat的jdk版本一致
这样部署到外部的tomcat后, request url需要在端口后加上项目的名字才能正常访问
*/
public class MyServletInitializer extends SpringBootServletInitializer {
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(App.class);
    }
    
}
