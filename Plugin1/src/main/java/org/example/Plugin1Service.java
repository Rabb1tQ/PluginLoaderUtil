package org.example;

import com.rabbitq.IPluginService;

public class Plugin1Service implements IPluginService {
    @Override
    public void service() {
        // 这里可以做插件需要做的任何事情，这里仅用一句打印表示插件的功能被调用
        System.out.println(name() + "功能调用");
    }

    @Override
    public String name() {
        return "插件一";
    }

    @Override
    public String version() {
        return "1.0";
    }
}
