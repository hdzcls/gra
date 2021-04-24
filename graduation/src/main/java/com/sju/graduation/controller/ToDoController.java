package com.sju.graduation.controller;

import com.sju.graduation.pojo.Needs;
import com.sju.graduation.pojo.Person;
import com.sju.graduation.pojo.Test;
import com.sju.graduation.service.LogService;
import com.sju.graduation.service.NeedsService;
import com.sju.graduation.service.PersonService;
import com.sju.graduation.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ToDoController {
    @Autowired
    private LogService logService;
    @Autowired
    private NeedsService needsService;
    @Autowired
    private TestService testService;
    @Autowired
    private PersonService personService;
    @GetMapping("/content/todoTask")
    public String toDoPage(HttpServletRequest request){
        Person person=(Person) request.getSession().getAttribute("person");
        if (person!=null&&person.getRole()==1){
            return "developerToDo";}
        else{
            return "testerToDo"; }
    }
    @GetMapping("/content/todo/findPersonalToDo")
    @ResponseBody
    public List personalToDo(HttpServletRequest request){
        Person person=(Person) request.getSession().getAttribute("person");
        int role=person.getRole();
        int id=person.getId();
        if(role==1){
            List<Needs> list=needsService.findPersonalNeed(id);
            return list;
        }else {
            return testService.findPersonalTest(id); }
    }
    @GetMapping("/content/todo/startTask")
    @ResponseBody
    public boolean startTask(HttpServletRequest request,int id){
        Person person=(Person) request.getSession().getAttribute("person");
        int role=person.getRole();
        personService.startTask(role,id);
        return true;
    }
    @GetMapping("/content/todo/endTask")
    @ResponseBody
    public boolean endTask(HttpServletRequest request,int id){
        Person person=(Person) request.getSession().getAttribute("person");
        int role=person.getRole();
        if(role==2){
            Test test=testService.findTestById(id);
            String action="测试<"+test.getNeedName()+">需求为通过";
        }
        personService.endTask(role,id);
        return true;
    }
    @PostMapping("content/todo/back")
    public String back(HttpServletRequest request){
        Person person=(Person) request.getSession().getAttribute("person");
        int role=person.getRole();
        String sid=request.getParameter("id");
        Integer id=Integer.parseInt(sid);
        String back=request.getParameter("back");
        String needName=request.getParameter("needName");
        Integer nid=needsService.findId(needName);
        needsService.back(back,nid);
        personService.endTask(role,id);
        return "testerToDo";
    }

}
