package com.sju.graduation.controller;

import com.sju.graduation.pojo.Project;
import com.sju.graduation.service.IndexService;
import com.sju.graduation.service.LogService;
import com.sju.graduation.service.PersonService;
import com.sju.graduation.service.ProjectSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private LogService logService;
    @Autowired
    private ProjectSevice projectSevice;
    @Autowired
    private IndexService indexService;
    @GetMapping("/content/index")
    public String index(HttpServletRequest request, Map map){
        Project project=projectSevice.findProject();
        map.put("project",project);
        return "index";
    }
    @GetMapping("/content/index/person")
    @ResponseBody
    public List countPerson(){
        return indexService.countPerson();
    }

    @GetMapping("/content/index/developeSpeed")
    @ResponseBody
    public Integer developeSpeed(){
        return indexService.developSpeed();
    }
    @GetMapping("/content/index/testSpeed")
    @ResponseBody
    public Integer testSpeed(){
        return indexService.testSpeed();
    }

    @GetMapping("/content/index/listAll")
    @ResponseBody
    public List listAll(){
        return indexService.listAll();
    }
    @PostMapping("/content/index/update")
    public String update(Project project){
        String action="修改了项目信息";
        logService.insertLog(action);
        projectSevice.update(project);
        return "redirect:/content/index";
    }

}
