package com.gjt.hellosb.component;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;


public class MyLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String l = request.getParameter("l");
        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(l)) {
            String[] split = l.split("_");
            if(split.length>1) {
                locale = new Locale(split[0],split[1]);
            }
            
            HttpSession session = request.getSession();  
            session.setAttribute("session_locale", locale);  
        }else {
            
            HttpSession session = request.getSession();  
            Locale localeInSession = (Locale) session.getAttribute("session_locale");  
            if(localeInSession != null) {  
                locale = localeInSession;  
            }
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        
    }

}
