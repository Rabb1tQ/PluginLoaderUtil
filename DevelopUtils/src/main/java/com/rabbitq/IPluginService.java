package com.rabbitq;

/*
 * @Author RabbitQ
 * @CreedDate: 2023/8/21 23:01
 * @description: 插件服务接口，插件项目需要提供实现这个接口的类，才能被主程序加载
 */

public interface IPluginService {

    /*
     *@Author：RabbitQ
     *@CreateData：2023/8/21 23:07
     *decription：插件功能主入口方法
     */
    void service();

    /*
     *@Author：RabbitQ
     *@CreateData：2023/8/21 23:07
     *decription：插件名成，通常用于展示在界面
     */
    String name();


    /*
     *@Author：RabbitQ
     *@CreateData：2023/8/21 23:07
     *decription：表示当前插件的版本
     */
    String version();

}