package cn.lindianyu.memory.web;

import cn.lindianyu.memory.inlet.YaMemoryInlet;
import cn.lindianyu.memory.service.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @Author: ya
 * @Email: 1002019117@qq.com
 * @Date:create in  2022/7/26
 */
@Controller
@RequestMapping
public class MemoryController {

    @Value("${server.port}")
    private String port;
    @Resource
    private YaMemoryInlet yaMemoryInlet;

    @Resource
    private MemoryService memoryService;

    @GetMapping(path = "ldySystem")
    public String ldySystem(){
        return "/ldyMIndex.html";
    }

    @GetMapping(path = "ldyMemoryGet")
    @ResponseBody
    public HashMap ldyMemoryGet(){
        return yaMemoryInlet.memoryMy;
    }

    @GetMapping(path = "ldyDumpDown")
    @ResponseBody
    public void ldyDumpDown(HttpServletResponse response){
        memoryService.ldyDumpDown(response);
    }
}
