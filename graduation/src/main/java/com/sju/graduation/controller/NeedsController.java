package com.sju.graduation.controller;

import com.sju.graduation.pojo.Needs;
import com.sju.graduation.pojo.Person;
import com.sju.graduation.service.NeedsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class NeedsController {
    @Autowired
    private NeedsService needsService;
    @GetMapping("/content/needs")
    public String needs(){
        return "needs";
    }
    @GetMapping("/content/needs/findAllNeeds")
    @ResponseBody
    public List<Needs> findAllNeeds(){
        return needsService.findAllNeeds();
    }
    @GetMapping("/content/needs/searchneeds")
    @ResponseBody
    public List<Needs> findNeedsByName(String name){
        return needsService.findNeedsByName(name);
    }
    @GetMapping("/content/needs/deleteNeeds")
    @ResponseBody
    public boolean deleteNeeds(int id){
        needsService.deleteNeeds(id);
        return true;
    }
    @PostMapping("/content/needs/addNeeds")
    public String addNeeds(Needs needs, HttpServletRequest request){
        Person person=(Person)request.getSession().getAttribute("person");
        needs.setWriter(person.getName());
        needsService.addNeeds(needs);
        return "redirect:/content/needs";

    }
    @GetMapping("/content/needs/findById")
    @ResponseBody
    public Needs findNeedsById(int id){
        return needsService.findNeedById(id);
    }
    @PostMapping("/content/needs/updateNeeds")
    public String updateNeeds(Needs needs, HttpServletRequest request){
        needsService.updateNeeds(needs);
        return "redirect:/content/needs";

    }


}
