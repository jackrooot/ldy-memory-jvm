package cn.lindianyu.memory.property;

import java.lang.management.ManagementFactory;

/**
 * @Author: ya
 * @Email: 1002019117@qq.com
 * @Date:create in  2022/8/10
 */
public class MemoryProperty {
    public static String pid;

    public static String regex = "[ ]+";

    static {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        pid = name.substring(0, name.indexOf("@"));
    }
}
