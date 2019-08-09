package com.gjt.hellosb.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gjt.hellosb.exception.BusinessException;

//自定义响应json数据

@ControllerAdvice
public class MyExceptionHandler {
    
    //1.self define response Json data, browser and postman get the same result
//    @ResponseBody
//    @ExceptionHandler(BusinessException.class)
//    public Map<String,String> handleException(Exception e){
//        Map<String,String> map = new HashMap<String,String>();
//        map.put("code", "my test");
//        map.put("message", e.getMessage());
//        return map;
//    }
    
    //2.self define response Json data, forward to error page. Browser and postman get different result
    @ExceptionHandler(BusinessException.class)
    public String handleException(Exception e, HttpServletRequest req){
        Map<String,String> map = new HashMap<String,String>();
        //default status_code=200,set it to 4xx or 5xx to make it go to self define error page
        req.setAttribute("javax.servlet.error.status_code", 500);
        map.put("myErrorCode", "myTestErrorCode");
        map.put("message", "This is myTestErrorMessage"); 
        
        //把自定义项加入到request中,然后在MyErrorAttribute中可以获取到
        req.setAttribute("myErrorMap", map);
        return "forward:/error";
    }
    
}
