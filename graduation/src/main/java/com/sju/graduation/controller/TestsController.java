package com.sju.graduation.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sju.graduation.pojo.Log;
import com.sju.graduation.pojo.Person;
import com.sju.graduation.pojo.Test;
import com.sju.graduation.service.LogService;
import com.sju.graduation.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@Controller
public class TestsController {
    @Autowired
    private LogService logService;
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
    public List searchtest(String name,int pageNum){
        PageHelper.startPage(pageNum,10);
        List<Test> list=testService.findLikeTest(name);
        PageInfo<Test> info=new PageInfo<Test>(list);
        List list1=new LinkedList();
        list1.add(list);
        list1.add(info);
        return list1;
    }
    @GetMapping("/content/tests/deleteTests")
    @ResponseBody
    public boolean deleteTests(int id){
        Test test=testService.findTestById(id);
        String action="删除了<"+test.getName()+">测试用例";
        logService.insertLog(action);
        testService.deleteTest(id);
        return true;
    }
    @PostMapping("/content/tests/addTests")
    public String addTests(Test test, HttpServletRequest request){
        String action="添加了<"+test.getName()+">测试用例";
        logService.insertLog(action);
        Person person=(Person) request.getSession().getAttribute("person");
        test.setWriter(person.getName());
        testService.addTest(test);

        return "redirect:/content/tests";
    }
    @PostMapping("/content/tests/updateTests")
    public String updateTests(Test test){
        String content=test.getContent();
        String action="修改了<"+test.getName()+">测试用例";
        logService.insertLog(action);
        testService.updateTest(test);

        return "redirect:/content/tests";
    }
    @GetMapping("/content/tests/findById")
    @ResponseBody
    public Test findTestById(int id){
        return testService.findTestById(id);
    }



}
