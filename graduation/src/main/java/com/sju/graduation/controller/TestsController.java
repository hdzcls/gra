package com.sju.graduation.controller;

import com.sju.graduation.pojo.Person;
import com.sju.graduation.pojo.Test;
import com.sju.graduation.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TestsController {
    @Autowired
    private TestService testService;
    @GetMapping("/content/tests")
    public String tests(){
        return "tests";
    }
    @GetMapping("/content/tests/findAllTest")
    @ResponseBody
    public List<Test> findAllTest(){
        return testService.findAllTest();
    }
    @GetMapping("/content/tests/searchtest")
    @ResponseBody
    public List<Test> searchtest(String name){
        return testService.findLikeTest(name);
    }
    @GetMapping("/content/tests/deleteTests")
    @ResponseBody
    public boolean deleteTests(int id){
        testService.deleteTest(id);
        return true;
    }
    @PostMapping("/content/tests/addTests")
    public String addTests(Test test, HttpServletRequest request){
        Person person=(Person) request.getSession().getAttribute("person");
        test.setWriter(person.getName());
        testService.addTest(test);

        return "redirect:/content/tests";
    }
    @PostMapping("/content/tests/updateTests")
    public String updateTests(Test test){
        String content=test.getContent();
        testService.updateTest(test);

        return "redirect:/content/tests";
    }
    @GetMapping("/content/tests/findById")
    @ResponseBody
    public Test findTestById(int id){
        return testService.findTestById(id);
    }



}
