package com.jd.v2.impl;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 不指定名称系统默认生成的名称为：jDRpcServer 这个是有坑坑，Bean会生成多个！！！
 */
@Component("jdRpcServer")
public class JDRpcServer implements ApplicationContextAware, InitializingBean {

    // private ApplicationContext applicationContext;

    //ExecutorService executorService = Executors.newFixedThreadPool(10);
    ExecutorService executorService = Executors.newCachedThreadPool();

    private int port;

    private Map<String, Object> handlerMap = new HashMap<>();

    public JDRpcServer(int port) {
        this.port = port;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);

            while (true) {
                // 接收rpc请求 阻塞
                Socket socket = serverSocket.accept();

                // 处理rpc请求
                executorService.execute(new ProcessorHandler(socket, handlerMap));
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        // this.applicationContext = applicationContext;

        // 按注解名称获取
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);
        if (!serviceBeanMap.isEmpty()) {
            serviceBeanMap.forEach((serviceName, serviceBean) -> {
                RpcService rpcService = serviceBean.getClass().getAnnotation(RpcService.class);
                String key = rpcService.value().getName();
                String version = rpcService.version();
                if (!StringUtils.isEmpty(version)) {
                    key = key + "-" + version;
                }
                handlerMap.put(key, serviceBean);
            });
        }

    }


}
