package com.sju.graduation.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sju.graduation.pojo.Log;
import com.sju.graduation.pojo.Module;
import com.sju.graduation.pojo.Person;
import com.sju.graduation.service.LogService;
import com.sju.graduation.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@Controller
public class ModuleController {
    @Autowired
    private LogService logService;
    @Autowired
    private ModuleService moduleService;
    @GetMapping("/content/module")
    public String modulePage(){
        return "module";
    }
    @GetMapping("/content/module/findAllModule")
    @ResponseBody
    public List<Module> findAllModule(){
        return moduleService.finAllModule();
    }
    @GetMapping("/content/module/deleteModule")
    @ResponseBody
    public boolean delete(int id){
        Module module=moduleService.findById(id);
        String action="删除了<"+module.getName()+">模块";
        logService.insertLog(action);
        moduleService.deleteModule(id);
        return true;
    }
    @GetMapping("/content/module/findByName")
    @ResponseBody
    public List findByName(String name,int pageNum){
        PageHelper.startPage(pageNum,10);
        List<Module> list=moduleService.findLikeName(name);
        PageInfo<Module> info=new PageInfo<Module>(list);
        List list1=new LinkedList();
        list1.add(list);
        list1.add(info);
        return list1;

    }
    @GetMapping("/content/module/findById")
    @ResponseBody
    public Module findById(int id){
        return moduleService.findById(id);

    }
    @PostMapping("/content/module/update")
    public String update(Module module){
        String action="修改了<"+module.getName()+">模块";
        logService.insertLog(action);
        moduleService.updateModule(module);
        return "redirect:/content/module"; }
    @PostMapping("/content/module/add")
    public String add(Module module, HttpServletRequest request){
        Person person = (Person)request.getSession().getAttribute("person");
        String name=person.getName();
        module.setWriter(name);
        String action="添加了<"+module.getName()+">模块";
        logService.insertLog(action);
        moduleService.add(module);
        return "redirect:/content/module";
    }
    @GetMapping("/content/module/checkByName")
    @ResponseBody
    public boolean check(String name){
        Integer falg=moduleService.check(name);
        if (falg!=null&&falg>0){
            return false;
        }else {
            return true; }
    }

}
