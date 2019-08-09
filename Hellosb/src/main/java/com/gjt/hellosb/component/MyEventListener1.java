package com.gjt.hellosb.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

//首先创建MyEventListener1类,然后springboot应用启动类中获取ConfigurableApplicationContext上下文，装载监听
public class MyEventListener1 implements ApplicationListener{

    private Logger logger = LoggerFactory.getLogger(MyEventListener1.class);
            
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        logger.info(String.format("%s Context装在监听器-监听到事件源：%s.", MyEventListener1.class.getName(), event.getSource()));
    }

}
