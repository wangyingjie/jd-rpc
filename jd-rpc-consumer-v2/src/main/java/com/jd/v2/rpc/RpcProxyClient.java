package com.jd.v2.rpc;

import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

@Component("rpcProxyClient")
public class RpcProxyClient {

    public <T> T getClientProxy(Class<T> interfaceClazz, String host, int port) {

        // 将接口放到数组里面去供生成接口的代理对象
        Object proxy = Proxy.newProxyInstance(interfaceClazz.getClassLoader(), new Class[]{interfaceClazz},
                new RemoteInvocationHandler(host, port));

        return (T) proxy;
    }

}
