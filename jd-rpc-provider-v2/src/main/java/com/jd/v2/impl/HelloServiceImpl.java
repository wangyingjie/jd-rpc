package com.jd.v2.impl;

import com.jd.bean.User;
import com.jd.service.IHelloService;

@RpcService(value = IHelloService.class, version = "1.0")
public class HelloServiceImpl implements IHelloService {

    @Override
    public String sayHello(String content) {
        System.out.println("request in: " + content);
        return "【1.0】Say Hello:" + content;
    }

    @Override
    public String saveUser(User user) {

        System.out.println("request in saveUser:" + user);

        return "【1.0】success";
    }
}
