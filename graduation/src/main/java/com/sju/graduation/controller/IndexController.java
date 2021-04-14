package com.sju.graduation.controller;

import com.sju.graduation.service.IndexService;
import com.sju.graduation.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private IndexService indexService;
    @GetMapping("/content/index")
    public String index(HttpServletRequest request){
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

}
