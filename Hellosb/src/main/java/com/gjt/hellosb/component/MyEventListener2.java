package com.gjt.hellosb.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

//创建MyEventListener2类，并使用@Component注解将该类装载入spring容器中
@Component
public class MyEventListener2 implements ApplicationListener<MyEvent> {
    private Logger logger = LoggerFactory.getLogger(MyEventListener2.class);
    
    @Override
    public void onApplicationEvent(MyEvent event) {
        logger.info(String.format("%s @Component监听器-监听到事件源：%s.", MyEventListener2.class.getName(), event.getSource()));
    }

}
