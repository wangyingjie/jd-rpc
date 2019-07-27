package com.jd.v2.impl;

import com.jd.bean.RpcRequest;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

public class ProcessorHandler implements Runnable {

    private final Socket socket;
    private Map<String, Object> handlerMap;


    public ProcessorHandler(Socket socket, Map<String, Object> handlerMap) {
        this.socket = socket;
        this.handlerMap = handlerMap;
    }

    @Override
    public void run() {

        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            // 输入流的参数信息
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();

            Object result = invoke(rpcRequest);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            objectOutputStream.writeObject(result);

            objectOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


    private Object invoke(RpcRequest rpcRequest) {

        String serviceName = rpcRequest.getClazzName();
        if (!StringUtils.isEmpty(rpcRequest.getVersion())) {
            serviceName += "-" + rpcRequest.getVersion();
        }

        Object service = handlerMap.get(serviceName);
        if (service == null) {
            throw new RuntimeException("service not found!" + rpcRequest.getClazzName());
        }

        // 获取请求参数
        Object[] parameters = rpcRequest.getParameters();
        Class<?>[] paramType = new Class<?>[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            // 获取参数类型
            paramType[i] = parameters[i].getClass();
        }

        try {
            Class clazz = Class.forName(rpcRequest.getClazzName());

            Method method = clazz.getMethod(rpcRequest.getMethodName(), paramType);

            Object invoke = method.invoke(service, parameters);

            return invoke;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
