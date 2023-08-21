package com.rabbitq;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.stream.Collectors;

/*
 * @Author：RabbitQ
 * @CreedDate：2023/8/21 23:08
 * @description：插件加载器，用于从插件目录加载所有插件的 IPluginService 实现类
 */
public class PluginLoader {

    /*
     *@Author：RabbitQ
     *@CreateData：2023/8/21 23:09
     *decription：插件加载的相对路径：这里表示所有的插件jar都放在主程序jar同级目录的plugins文件夹下
     */
    public static final String PLUGIN_PATH = "plugins";

    public static List<IPluginService> loadPlugins() throws MalformedURLException {
        List<IPluginService> plugins = new ArrayList<>();

        File parentDir = new File(PLUGIN_PATH);
        File[] files = parentDir.listFiles();
        if (null == files) {
            return Collections.emptyList();
        }

        // 从目录下筛选出所有jar文件
        List<File> jarFiles = Arrays.stream(files)
                .filter(file -> file.getName().endsWith(".jar"))
                .collect(Collectors.toList());

        URL[] urls = new URL[jarFiles.size()];
        for (int i = 0; i < jarFiles.size(); i++) {
            // 加上 "file:" 前缀表示本地文件
            urls[i] = new URL("file:" + jarFiles.get(i).getAbsolutePath());
        }

        URLClassLoader urlClassLoader = new URLClassLoader(urls);
        // 使用 ServiceLoader 以SPI的方式加载插件包中的 IPluginService 实现类
        ServiceLoader<IPluginService> serviceLoader = ServiceLoader.load(IPluginService.class, urlClassLoader);
        for (IPluginService iPluginService : serviceLoader) {
            plugins.add(iPluginService);
        }
        return plugins;
    }
}