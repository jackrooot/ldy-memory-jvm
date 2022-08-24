package cn.lindianyu.memory.service;

import cn.lindianyu.memory.menum.MemoryEnum;
import cn.lindianyu.memory.property.MemoryProperty;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Author: ya
 * @Email: 1002019117@qq.com
 * @Date:create in  2022/8/10
 */
@Service
public class MemoryServiceImpl implements MemoryService{

    @Override
    public void ldyDumpDown(HttpServletResponse response) {
        File file1=null;
        MemoryEnum cmd = MemoryEnum.DUMP_LINUX;
        OutputStream out = null;
        InputStream inputStream = null;
        try {
            String property = System.getProperty("os.name").toLowerCase();
            if(property.contains("windows")){
                cmd=MemoryEnum.DUMP_WINDOWS;
            }else{
                cmd=MemoryEnum.DUMP_LINUX;
            }
            File file = new File(cmd.TIME);
            if(!file.exists()){
                boolean mkdirs = file.mkdirs();
            }
            Process exec = Runtime.getRuntime().exec(cmd.CMD + MemoryProperty.pid);
            exec.waitFor();

            file1 = new File(cmd.TIME+cmd.FILENAME);
            inputStream = new FileInputStream(file1);
            String headStr = "attachment; filename=jvm.dump";
            response.setHeader("Content-Disposition",headStr);
            out = response.getOutputStream();

            byte[] bytes= new byte[4096];
            int len;
            while ((len=inputStream.read(bytes))!=-1){
                out.write(bytes,0,len);
            }
            out.flush();
        } catch (Exception e) {
        } finally {
            try {
                if(null!=out){
                    out.close();
                }
                if(null!=inputStream){
                    inputStream.close();
                }
            } catch (IOException e) {
            }
        }
        file1=new File(cmd.TIME+cmd.FILENAME);
        boolean delete = file1.delete();
    }
}
