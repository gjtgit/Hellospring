package com.gjt.hellosb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

import com.gjt.hellosb.component.MyEvent;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@EnableCaching  //开启缓存
public class App 
{
    public static void main( String[] args )
    {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        
        //SpringApplicationContext的生命周期事件为
        //starting,environmentPrepared,contextPrepared,contextLoaded,finished
        
        //4种事件监听器顺序为 配置文件,@EventListener,@Component,Context装载
        //装载事件
        //context.addApplicationListener(new MyEventListener1());
        
        //发布事件
        context.publishEvent(new MyEvent("测试事件"));
        
        System.out.println("------ Hello World! ------" );
    }
    
    
}
