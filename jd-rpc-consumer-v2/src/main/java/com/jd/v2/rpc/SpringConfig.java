package com.jd.v2.rpc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.jd")
public class SpringConfig {

    @Bean(name = "rpcProxyClient")
    public RpcProxyClient rpcProxyClient() {
        return new RpcProxyClient();
    }

}
