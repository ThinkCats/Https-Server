package com.https.busi.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

/**
 * Created by Wanglei on 15/11/28.
 */

@Configuration
@PropertySource("classpath:application-${env}.properties")
public class PropertyConfig implements EnvironmentAware {

    private Environment environment;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Override
    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }
}
