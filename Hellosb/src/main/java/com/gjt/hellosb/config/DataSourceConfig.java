package com.gjt.hellosb.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages = {DataSourceConfig.MAPPER_PACKAGE}, sqlSessionTemplateRef  = "priSqlSessionTemplate")
public class DataSourceConfig {

    public static final String MAPPER_PACKAGE = "com.gjt.hellosb.dao";

    @Value("${mybatis.mapper-locations}")
    private String MAPPER_XML_LOCATIONS;
    @Value("${mybatis.type-aliases-package}")
    private String TYPE_ALIASES_PACKAGE;

    @Bean(name = "priDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "priSqlSessionFactory")
    //@ConfigurationProperties(prefix = "mybatis")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("priDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_XML_LOCATIONS));
        bean.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
        return bean.getObject();
    }

    @Bean(name = "priSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("priSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    @Bean(name = "priTransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("priDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    
}
