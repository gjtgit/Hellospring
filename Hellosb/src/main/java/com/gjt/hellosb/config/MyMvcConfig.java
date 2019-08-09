package com.gjt.hellosb.config;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gjt.hellosb.component.DateConverter;
import com.gjt.hellosb.component.LoginHandlerInterceptor;
import com.gjt.hellosb.component.MyLocaleResolver;

//use WebMvcConfigurer to extends springMVC, auto config can also work when no @EnableWebMvc
//use @EnableWebMvc will take over, auto config will not work
//@EnableWebMvc 

@Configuration
public class MyMvcConfig implements WebMvcConfigurer{
    
    private Logger logger = LoggerFactory.getLogger(MyMvcConfig.class);
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        if (logger.isInfoEnabled())
            logger.info("******** MyMvcConfig >> addViewControllers [/myMvc,/,/index] Start *************");
        registry.addViewController("myMvc").setViewName("hello");
        //add self define view controller so it can go to pages in templates dir
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/dashboard").setViewName("dashboard");
        
    }
    
    //add self define LocaleResolver to process locale switch on the page
    //must use localeResolver as BeanName because it is defined in DispatcherServlet
    @Bean 
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver(); 
    }
    
    //have to login first otherwise go to login page
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //for static resources, Springboot will handle
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
            .excludePathPatterns("/", "/index", "/login", "/doLogin");
    }

    //extends self define viewResolver(use @Bean),just test if it can be added
    //better to use localeResolver as BeanName because it is defined in DispatcherServlet
    @Bean
    public ViewResolver viewResolver() {
        return new MyViewResolver();
    }
    
    private static class MyViewResolver implements ViewResolver {
        @Override
        public View resolveViewName(String viewName, Locale locale) throws Exception {
            return null;
        }
    }
    
    //convert the date string from Front to Date, so Backend can handle it
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateConverter());
    }
    
}
