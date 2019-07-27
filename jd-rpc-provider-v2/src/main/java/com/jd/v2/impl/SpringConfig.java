package com.jd.v2.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.jd")
public class SpringConfig {

    @Bean(name = "jdRpcServer")
    public JDRpcServer jdRpcServer() {
        return new JDRpcServer(8888);
    }

}
