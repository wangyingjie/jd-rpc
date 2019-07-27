package com.jd;

import com.jd.service.IHelloService;
import com.jd.service.impl.HelloServiceImpl;
import com.jd.service.impl.RpcProxyServer;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {

        IHelloService helloService = new HelloServiceImpl();

        RpcProxyServer rpcProxyServer = new RpcProxyServer();

        System.out.println("server start************");

        rpcProxyServer.publisher(8888, helloService);


    }
}
