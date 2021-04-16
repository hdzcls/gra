package com.sju.graduation.controller;

import com.sju.graduation.pojo.Person;
import com.sju.graduation.pojo.Test;
import com.sju.graduation.service.NeedsService;
import com.sju.graduation.service.PersonService;
import com.sju.graduation.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ToDoController {
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
            return "testerToDo";
    }
    }
    @GetMapping("/content/todo/findPersonalToDo")
    @ResponseBody
    public List personalToDo(HttpServletRequest request){
        Person person=(Person) request.getSession().getAttribute("person");
        int role=person.getRole();
        int id=person.getId();
        if(role==1){
            return needsService.findPersonalNeed(id);
        }else {
            return testService.findPersonalTest(id);
        }



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
        personService.endTask(role,id);
        return true;



    }
}
