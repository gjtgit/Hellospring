package com.gjt.hellosb;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.gjt.hellosb.config.MyTestProp;
import com.gjt.mystarter.MyStarterService;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MyTest {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    DataSource dataSource;
    
    @Autowired
    DataSourceProperties dataSourceProperties;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private MyStarterService myStarterService;
    
    @Resource(name = "priDataSource") 
    private DataSource priDataSource;

    @Autowired
    private MyTestProp myTestProp;
    
    @Before
    public void testBefore(){
        logger.info("before method test");
    }
    
    @After
    public void testAfter(){
        logger.info("after method test");
    }
    
    @Test
    public void contextLoads() { //执行SQL,输出查到的数据
        JdbcTemplate jdbcTemplate = new JdbcTemplate(priDataSource);
        List<?> resultList = jdbcTemplate.queryForList("select * from user");
        logger.info("priDataSource===>>>>>>>>>>>" + resultList.size());
    }
    
    @Test
    public void testDatSource() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<?> resultList = jdbcTemplate.queryForList("select * from user");
        logger.info("dataSource===>>>>>>>>>>>" + resultList.size());
    }
    
    @Test
    public void getMyTestProp() {
        logger.info(myTestProp.getJdbcUrl());
    }
    
    @Test
    public void testMyStarter() {
        logger.info(myStarterService.sayHello());
    }
}
