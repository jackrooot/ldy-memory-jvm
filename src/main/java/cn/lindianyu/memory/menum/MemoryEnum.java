package cn.lindianyu.memory.menum;

/**
 * @Author: ya
 * @Email: 1002019117@qq.com
 * @Date:create in  2022/8/10
 */
public enum MemoryEnum {
    GC("jstat -gc "," 2000",""),
    DUMP_WINDOWS("jmap -dump:file=C:/temp/jvm.dump ","C:/temp/","jvm.dump"),
    DUMP_LINUX("jmap -dump:file=/temp/jvm.dump ","/temp/","jvm.dump"),
    ;
    public String CMD;

    public String TIME;

    public String FILENAME;
    MemoryEnum(String CMD, String TIME,String FILENAME) {
        this.CMD = CMD;
        this.TIME = TIME;
        this.FILENAME=FILENAME;
    }
}
