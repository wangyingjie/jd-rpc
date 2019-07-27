package com.jd;

import com.jd.rpc.RpcProxyClient;
import com.jd.service.IHelloService;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {

        RpcProxyClient rpcProxyClient = new RpcProxyClient();

        IHelloService helloService = rpcProxyClient.getClientProxy(IHelloService.class, "localhost", 8888);

        String mic = helloService.sayHello("mic");

        System.out.println(mic);

    }
}
