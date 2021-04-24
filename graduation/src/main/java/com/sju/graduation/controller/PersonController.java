package com.sju.graduation.controller;

import com.sju.graduation.mapper.PersonMapper;
import com.sju.graduation.pojo.Person;
import com.sju.graduation.service.LogService;
import com.sju.graduation.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PersonController {
    @Autowired
    private LogService logService;
    @Autowired
    private PersonService personService;
    @GetMapping("/content/person/tester")
    public String tester(){
        return "tester";
    }
    @GetMapping("/content/person/developer")
    public String developer(){
        return "developer";
    }
    @GetMapping("/content/person/findAllDeveloper")
    @ResponseBody
    public List<Person> findAllDeveloper(){
        return personService.findAll(1);
    }
    @GetMapping("/content/person/findAllTestester")
    @ResponseBody
    public List<Person> findAllTestester(){
        return personService.findAll(2);
    }
    @PostMapping("/content/person/addDeveloper")
    public String addDeveloper(@Validated Person person){
        String action="添加了<"+person.getName()+">开发人员";
        logService.insertLog(action);
        personService.addPserson(person,1);
        return "redirect:/content/person/developer";
    }
    @PostMapping("/content/person/addTester")
    public String addTester(@Validated Person person){
        String action="添加了<"+person.getName()+">测试人员";
        logService.insertLog(action);
        personService.addPserson(person,2);
        return "redirect:/content/person/tester";
    }
    @GetMapping("/content/person/check")
    @ResponseBody
    public boolean check(String logname){
        Person person = personService.findByLogname(logname);
        if (person==null){
            return true;
        }else {
            return false;
        }
    }
    @GetMapping("/content/person/delete")
    @ResponseBody
    public boolean delete(int id){
        Person person=personService.findById(id);
        String p;
        if(person.getRole()==1){
            p="开发人员";
        }else {
            p="测试人员";
        }
        String action="删除了<"+person.getName()+">"+p;
        logService.insertLog(action);
        personService.deletePerson(id);
        return true;
    }
    @GetMapping("/content/person/findByLogname")
    @ResponseBody
    public Person findByLogname(String logname){
        Person person=personService.findByLogname(logname);
        return person;

    }
    @PostMapping("/content/person/updatedeveloper")
    public String updateDeveloper(Person person){
        Person person1=personService.findById(person.getId());
        String p;
        if(person1.getRole()==1){
            p="开发人员";
        }else {
            p="测试人员";
        }
        String action="修改了<"+person1.getName()+">"+p;
        logService.insertLog(action);
        personService.updatePerson(person);
        return "redirect:/content/person/developer";

    }
    @PostMapping("/content/person/updatetester")
    public String updateTester(Person person){
        personService.updatePerson(person);
        return "redirect:/content/person/tester";

    }
    @GetMapping("/content/person/searchtester")
    @ResponseBody
    public List<Person> serchertester(String name){
        return personService.findByName(name,2);
    }
    @GetMapping("/content/person/searchdeveloper")
    @ResponseBody
    public List<Person> sercherdeveloper(String name){
        return personService.findByName(name,1);
    }







}
