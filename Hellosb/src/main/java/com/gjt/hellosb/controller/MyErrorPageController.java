package com.gjt.hellosb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//自定义错误页配置MyErrorPageConfig和MyErrorPageController
//可以不采用自定义配置,只放一个4或5开头的页面到templates\error目录就可以替换默认错误页面
//@Controller
public class MyErrorPageController {
    
    @RequestMapping("error-404")
    public String toPage404(){
        //强制重定向到static
        //return "redirect:/error/error-404.html";  
        //引入了thymeleaf组件，动态跳转会覆盖默认的静态跳转，默认就会跳转到/templates
        return "error/error-404";
    }
    
    @RequestMapping("error-500")
    public String toPage500(){
        return "error/error-500";
    }
    
}
