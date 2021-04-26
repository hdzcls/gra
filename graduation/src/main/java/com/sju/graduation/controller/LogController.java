package com.sju.graduation.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sju.graduation.pojo.Log;
import com.sju.graduation.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class LogController {
    @Autowired
    private LogService logService;
    @GetMapping("/content/log")
    public String logPage(Map map){
        PageHelper.startPage(1,5);
        List<Log> list=logService.findAllLog();
        PageInfo<Log> info=new PageInfo<Log>(list);
        map.put("info",info);
        return "log";
    }
    @GetMapping("/content/log/findAll")
    @ResponseBody
    public List findAll(int pageNum){
        PageHelper.startPage(pageNum,5);
        List<Log> list=logService.findAllLog();
        PageInfo<Log> info=new PageInfo<Log>(list);
        List list1=new LinkedList();
        list1.add(list);
        list1.add(info);
        return list1;
    }
    @GetMapping("/content/log/findByPerson")
    @ResponseBody
    public List<Log> findByPerson(String name){
        return logService.findByPerson(name);
    }
}
