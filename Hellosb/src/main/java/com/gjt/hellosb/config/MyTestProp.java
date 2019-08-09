package com.gjt.hellosb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class MyTestProp {
    private String userName;
    private String password;
    private String jdbcUrl;
    private String driverClass;
    
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getJdbcUrl() {
        return jdbcUrl;
    }
    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }
    public String getDriverClass() {
        return driverClass;
    }
    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }
    
}
