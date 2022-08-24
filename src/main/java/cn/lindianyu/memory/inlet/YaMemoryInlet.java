package cn.lindianyu.memory.inlet;

import cn.lindianyu.memory.menum.MemoryEnum;
import cn.lindianyu.memory.property.MemoryProperty;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * @Author: ya
 * @Email: 1002019117@qq.com
 * @Date:create in  2022/7/11
 */
@Component
public class YaMemoryInlet implements CommandLineRunner {

    public HashMap<String,String> memoryMy = new HashMap<>();

    @Override
    public void run(String... args) throws Exception {
        Process exec = Runtime.getRuntime().exec(MemoryEnum.GC.CMD + MemoryProperty.pid +MemoryEnum.GC.TIME);
        InputStream inputStream = exec.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line=null;
        int start=0;
        String[] namesTitle = new String[0];
        while ((line= bufferedReader.readLine())!=null){
            if(start == 0){
                namesTitle =  line.trim().split(MemoryProperty.regex);
            }else{
                String[] split = line.trim().split(MemoryProperty.regex);
                for (int i = 0; i < namesTitle.length; i++) {
                    memoryMy.put(namesTitle[i],split[i]);
                }
            }
            start++;
        }
    }
}
