package com.gjt.hellosb.component;

import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

//servlet.error.DefaultErrorAttributes 中getErrorAttributes定义了返回的项
//如果要自定义,可以继承它,重新getErrorAttributes方法,并加入自定义项
@Component
public class MyErrorAttribute extends DefaultErrorAttributes {
    //DefaultErrorAttributes类默认不加入exception项到ErrorAttribute
    public MyErrorAttribute() {
        super(true); //加入exception
    }
    
    //此map包含的项就是错误页面能够获得的项，如status,timestamp,path,exception,message
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        errorAttributes.put("myErrorAttr1", "GJT's error attribute");
        //取出MyExceptionHandler中自定义项,加入到默认错误项中
        errorAttributes.put("myErrorMap", webRequest.getAttribute("myErrorMap", RequestAttributes.SCOPE_REQUEST));
        return errorAttributes;
    }
}
