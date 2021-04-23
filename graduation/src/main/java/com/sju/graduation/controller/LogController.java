package com.sju.graduation.controller;

import com.sju.graduation.pojo.Log;
import com.sju.graduation.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LogController {
    @Autowired
    private LogService logService;
    @GetMapping("/content/log")
    public String logPage(){
        return "log";
    }
    @GetMapping("/content/log/findAll")
    @ResponseBody
    public List<Log> findAll(){
        return logService.findAllLog();
    }
    @GetMapping("/content/log/findByPerson")
    @ResponseBody
    public List<Log> findByPerson(String name){
        return logService.findByPerson(name);
    }
}
