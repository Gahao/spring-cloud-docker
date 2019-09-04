package com.musketeers.classserver.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.config.GlobalConfig.DbConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisMapperRefresh;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Mybatis配置项 by fanjiahao 20190403
 */
@Configuration
@Slf4j
public class MybatisPlusConfig {

    // 环境标志, 区分dev or prod
    @Value("${spring.profiles.active}")
    private String projectStage;

    /**
     * 数据源a 相关信息
     */
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${enabled}")
    private Boolean enabled;

    @Value("${mappingPath}")
    private String mappingPath;

    @Value("${delaySeconds}")
    private Integer delaySeconds;

    @Value("${sleepSeconds}")
    private Integer sleepSeconds;

    // 创建数据源
    @Bean
    public DruidDataSource dataSource(){
        DruidDataSource d = new DruidDataSource();
        d.setUrl(url);
        d.setUsername(username);
        d.setPassword(password);
        return d;
    }

    /**
     * MapperScannerConfigurer 是 BeanFactoryPostProcessor 的一个实现，如果配置类中出现 BeanFactoryPostProcessor ，会破坏默认的
     * post-processing, 如果不加static, 会导致整个都提前加载, 这时候, 取不到projectStage的值
     *
     * @return
     */
    @Bean
    public static MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.musketeers.classserver");
        // 设置为上面的 factory name
        configurer.setSqlSessionFactoryBeanName("mybatisSqlSessionFactoryBean");
        return configurer;
    }

    // 创建全局配置
    @Bean
    public GlobalConfig globalConfig() {
        // 全局配置文件
        GlobalConfig globalConfig = new GlobalConfig();
        DbConfig dbConfig = new DbConfig();
        // 默认为自增
        //dbConfig.setIdType(IdType.AUTO);
        // 手动指定db 的类型, 这里是mysql
        dbConfig.setDbType(DbType.MYSQL);
        globalConfig.setDbConfig(dbConfig);
        if (projectStage.equals("dev")) {
            // 如果是dev环境,则使用 reload xml的功能,方便调试
            globalConfig.setRefresh(true);
        }
        // 逻辑删除注入器
        //LogicSqlInjector injector = new LogicSqlInjector();
        //globalConfig.setSqlInjector(injector);
        return globalConfig;
    }

    @Bean
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean(
            DruidDataSource dataSource,
            GlobalConfig globalConfig) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setGlobalConfig(globalConfig);
        List<Interceptor> interceptors = new ArrayList<>();
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置分页插件
        interceptors.add(paginationInterceptor);
        log.info("##############  目前运行环境为 " + projectStage + "  ##############");
        // 如果是dev环境,打印出sql, 设置sql拦截插件, prod环境不要使用, 会影响性能
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        interceptors.add(performanceInterceptor);

        sqlSessionFactoryBean.setPlugins(interceptors.toArray(new Interceptor[0]));
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setDataSource(dataSource());//数据源
        sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources(mappingPath));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.musketeers.classserver");//别名，让*Mpper.xml实体类映射可以不加上具体包名
        return sqlSessionFactoryBean;
    }

    @Bean
    public MybatisMapperRefresh mybatisMapperRefresh(MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean)
            throws Exception {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        MybatisMapperRefresh mybatisMapperRefresh = new MybatisMapperRefresh(
                resourcePatternResolver.getResources(mappingPath),
                mybatisSqlSessionFactoryBean.getObject(),
                delaySeconds,
                sleepSeconds,
                enabled
        );
        return mybatisMapperRefresh;
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

}
