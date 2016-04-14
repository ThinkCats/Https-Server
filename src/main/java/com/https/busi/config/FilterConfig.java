package com.https.busi.config;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Created by wanglei25 on 2016/4/8.
 */

@Order(3)
@Configuration
public class FilterConfig {

    //Character Encoding Filter
    @Bean
    public FilterRegistrationBean characterFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new CharacterEncodingFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("Character Filter");
        return filterRegistrationBean;
    }


}
