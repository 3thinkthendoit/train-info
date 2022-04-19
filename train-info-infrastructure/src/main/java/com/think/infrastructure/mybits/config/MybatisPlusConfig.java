package com.think.infrastructure.mybits.config;

import com.think.infrastructure.mybits.Injector.BatchSqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hg
 * @date 2022-04-08日 16:15
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public BatchSqlInjector easySqlInjector() {
        return new BatchSqlInjector();
    }
}
