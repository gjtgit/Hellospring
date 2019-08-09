package com.gjt.hellosb.config;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.Servlet;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gjt.hellosb.filter.MyFilter;
import com.gjt.hellosb.myListener.MyListener;
import com.gjt.hellosb.servlet.MyServlet;

@Configuration
public class MyServletConfig {
    
    //定制嵌入的servlet容器
//  @Bean
//  public WebServerFactoryCustomizer<ConfigurableWebServerFactory> myWebServerCustomizer() {
//      return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
//          @Override
//          public void customize(ConfigurableWebServerFactory factory) {
//              factory.setPort(8081);  //修改服务器的端口,此处会覆盖配置文件里的端口设置
//          }
//      };
//  }
    
    //注册servlet3大组件, servlet,filter,listener
    //servlet
    @Bean
    public ServletRegistrationBean<Servlet> myServlet() {
        return new ServletRegistrationBean<Servlet>(new MyServlet(), "/myServlet");
    }
    
    //filter
    @Bean
    public FilterRegistrationBean<Filter> myFilter() {
        FilterRegistrationBean<Filter> filterBean = new FilterRegistrationBean<Filter>();
        filterBean.setFilter(new MyFilter());
        List<String> l = new ArrayList<String>();
        l.add("/myServlet");
        l.add("/hello");
        filterBean.setUrlPatterns(l);
        return filterBean;
    }
    
    //listener
    @Bean
    public ServletListenerRegistrationBean<MyListener> myListener() {
        return new ServletListenerRegistrationBean<MyListener>(new MyListener());
    }
    
}
