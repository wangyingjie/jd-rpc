package com.jd.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class RpcRequest implements Serializable {

    private String clazzName;

    private String methodName;

    private Object[] parameters;

    private String version;

}
