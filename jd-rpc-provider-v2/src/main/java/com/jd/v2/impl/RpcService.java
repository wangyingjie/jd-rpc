package com.jd.v2.impl;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) //类/接口
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RpcService {

    /**
     * 拿到服务端的接口列表
     *
     * @return
     */
    Class<?> value();

    /**
     * 版本
     *
     * @return
     */
    String version();

}
