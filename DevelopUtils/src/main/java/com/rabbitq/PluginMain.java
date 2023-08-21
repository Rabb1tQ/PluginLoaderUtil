package com.rabbitq;

import java.net.MalformedURLException;
import java.util.List;

/*
 * @Author：RabbitQ
 * @CreedDate：2023/8/21 23:10
 * @description：调用插件验证类
 */
public class PluginMain {
    public static void main(String[] args) throws MalformedURLException {
        System.out.println("开始加载插件");
        List<IPluginService> services = PluginLoader.loadPlugins();
        System.out.println(services.size() + "个插件加载成功\n");

        for (int i = 0; i < services.size(); i++) {
            IPluginService service = services.get(i);
            System.out.println("===插件" + i + "===");
            System.out.println("插件名：" + service.name());
            System.out.println("版本号：" + service.version());
            System.out.println("插件服务启动：");
            service.service();
        }
    }
}
