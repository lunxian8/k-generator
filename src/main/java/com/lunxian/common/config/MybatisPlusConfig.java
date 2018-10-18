package com.lunxian.common.config;

import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Wang
 * \* Date: 2018/6/4
 * \* Time: 9:31
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//Spring boot方式
@EnableTransactionManagement
@Configuration
@MapperScan("com.lunxian.modules.*.dao")
public class MybatisPlusConfig {
     /**
      * SQL执行效率插件
      */
     @Bean
     //@Profile({"dev","test"})// 设置 dev test 环境开启
     public PerformanceInterceptor performanceInterceptor() {
          return new PerformanceInterceptor();
     }

}