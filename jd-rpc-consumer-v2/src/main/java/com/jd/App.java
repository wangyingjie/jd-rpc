package com.jd;

import com.jd.service.IHelloService;
import com.jd.v2.rpc.RpcProxyClient;
import com.jd.v2.rpc.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        RpcProxyClient proxyClient = context.getBean(RpcProxyClient.class);

        IHelloService helloService = proxyClient.getClientProxy(IHelloService.class, "localhost", 8888);
        String mic = helloService.sayHello("mic");

        System.out.println(mic);
    }


}
