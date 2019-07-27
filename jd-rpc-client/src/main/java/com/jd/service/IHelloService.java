package com.jd.service;

import com.jd.bean.User;

public interface IHelloService {

    String sayHello(String content);

    String saveUser(User user);
}
