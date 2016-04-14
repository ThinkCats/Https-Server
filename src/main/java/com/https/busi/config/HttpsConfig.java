package com.https.busi.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.Ssl;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wanglei25 on 2016/4/9.
 */

@Configuration
public class HttpsConfig {

    //config https
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return configurableEmbeddedServletContainer -> {
            Ssl ssl= new Ssl();
            ssl.setKeyStore("F:/key/mykeystore");
            ssl.setKeyStorePassword("changeit");
            configurableEmbeddedServletContainer.setSsl(ssl);
            configurableEmbeddedServletContainer.setPort(8447);
        };
    }

    //config whole site https , but reserved http at the same time
    @Bean
    public EmbeddedServletContainerFactory containerFactory(){
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory(){
            @Override
            protected void postProcessContext(Context context){
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection securityCollection = new SecurityCollection();
                securityCollection.addPattern("/*");
                securityConstraint.addCollection(securityCollection);
                context.addConstraint(securityConstraint);
            }
        };
        factory.addAdditionalTomcatConnectors(createHttpConnector());
        return factory;
    }

    //reserved http style
    private Connector createHttpConnector(){
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol"); //NIO protocol
        connector.setScheme("http");
        connector.setSecure(false);
        connector.setPort(8080);
        connector.setRedirectPort(8447);
        return connector;
    }

}
