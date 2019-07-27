package com.jd.v2.impl;

import com.jd.bean.User;
import com.jd.service.IHelloService;

@RpcService(value = IHelloService.class, version = "2.0")
public class HelloServiceImpl2 implements IHelloService {

    @Override
    public String sayHello(String content) {
        System.out.println("request in: " + content);
        return "【2.0】Say Hello:" + content;
    }

    @Override
    public String saveUser(User user) {

        System.out.println("request in saveUser:" + user);

        return "【2.0】success";
    }
}
