package com.jd.service.impl;

import com.jd.bean.User;
import com.jd.service.IHelloService;

public class HelloServiceImpl implements IHelloService {

    @Override
    public String sayHello(String content) {
        System.out.println("request in: " + content);
        return "Say Hello:" + content;
    }

    @Override
    public String saveUser(User user) {

        System.out.println("request in saveUser:" + user);

        return "success";
    }
}
