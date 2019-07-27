package com.jd.rpc;

import com.jd.bean.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

// 客户端实现服务端的代理对象
public class RemoteInvocationHandler implements InvocationHandler {

    private String host;
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("runing");

        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClazzName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);

        RpcNetTransport rpcNetTransport = new RpcNetTransport(host, port);

        Object result = rpcNetTransport.send(rpcRequest);



        return result;
    }




}
