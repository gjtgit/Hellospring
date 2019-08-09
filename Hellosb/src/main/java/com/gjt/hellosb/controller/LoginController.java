package com.gjt.hellosb.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;


@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);
    
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    
    @PostMapping("/doLogin")
    public String doLogin(@RequestParam("username") String username, 
                        @RequestParam("password") String password,
                        Map<String,Object> map,
                        HttpServletRequest req) {
        if (logger.isInfoEnabled())
            logger.info("******** LoginController >> doLogin Start *************");
        
        if( StringUtils.equals("admin", password)){
            req.getSession().setAttribute("loginUser", username);
            //return "dashboard";         //when jump to dashboard page, press F5 will cause repeat submit, need to use redirect
            return "redirect:/dashboard"; //use redirect to dashboard page to avoid repeat submit,need to add viewResolver
        }else {
            map.put("loginMsg", "password is wrong");
            return "login";
        }
        
    }
}
