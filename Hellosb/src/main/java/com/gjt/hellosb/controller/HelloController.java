package com.gjt.hellosb.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gjt.hellosb.exception.BusinessException;
import com.gjt.mystarter.MyStarterService;

@Controller
public class HelloController {
    
    private Logger logger = LoggerFactory.getLogger(HelloController.class);
            
    @Autowired
    private MyStarterService myStarterService;
    
    @ResponseBody
    @RequestMapping("/mystarter")
    public String myStarter() {
        if (logger.isInfoEnabled())
            logger.info("******** HelloController >> myStarter Start *************");
        return myStarterService.sayHello();
    }
    
    @RequestMapping("/hello")
    public String hello(Map<String,Object> map) {
        if (logger.isInfoEnabled())
            logger.info("******** HelloController >> hello Start *************");
        map.put("msg","Msg from backend!");
        return "hello";
    }
    
    //jump to templates page not public dir, can add viewResolver in WebMvcConfigurer to get the same result
//    @RequestMapping({"/","/index"})
//    public String index() {
//        return "index";
//    }
    
    //throw an exception, MyExceptionHandler will catch and return self define Json data
    @ResponseBody
    @RequestMapping("/exception/{flag}")
    public String exception(@PathVariable String flag) throws BusinessException {
        if (logger.isInfoEnabled())
            logger.info("******** HelloController >> exception Start *************");
        if("true".equals(flag)) {
            throw new BusinessException("Just a test message");
        }
        return "test exception";
    }
    
}
