package com.gjt.hellosb.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

//创建MyEventListener4类，并使用@Component注解将该类装载入spring容器中
//无需实现ApplicationListener接口，使用@EventListener装饰具体方法
@Component
public class MyEventListener4{
    private Logger logger = LoggerFactory.getLogger(MyEventListener4.class);
    
    @EventListener
    public void onApplicationEvent(MyEvent event) {
        logger.info(String.format("%s @EventListener监听器-监听到事件源：%s.", MyEventListener4.class.getName(), event.getSource()));
    }

}
