package com.gjt.hellosb.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

//创建MyEventListener3类，然后在application.properties中配置监听
public class MyEventListener3 implements ApplicationListener<MyEvent> {
    private Logger logger = LoggerFactory.getLogger(MyEventListener3.class);
    
    @Override
    public void onApplicationEvent(MyEvent event) {
        logger.info(String.format("%s 配置文件监听器-监听到事件源：%s.", MyEventListener3.class.getName(), event.getSource()));
    }

}
