package com.gjt.hellosb.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

//自定义错误页配置MyErrorPageConfig和MyErrorPageController
//可以不采用自定义配置,只放一个4或5开头的页面到templates\error目录就可以替换默认错误页面
//self define MyErrorPageConfig and MyErrorPageController
//for thymeleaf,can just put a 4xx.html in the error folder under templates
//@Configuration
public class MyErrorPageConfig {
     
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            public void customize(ConfigurableWebServerFactory factory) {
                ErrorPage errorPage400 = new ErrorPage(HttpStatus.BAD_REQUEST,
                        "/error-400");
                ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND,
                        "/error-404");
                ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,
                        "/error-500");
                factory.addErrorPages(errorPage400, errorPage404,
                        errorPage500);
            }
        };
    }

}
