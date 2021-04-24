package com.sju.graduation.controller;

import com.sju.graduation.mapper.PersonMapper;
import com.sju.graduation.pojo.Person;
import com.sju.graduation.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private PersonService personService;
    @GetMapping(value = "/")
    public String hello1(){
        return "login";
    }
    @PostMapping(value = "/login")
    public String login(HttpServletRequest request, Map map){
        String logname=request.getParameter("name");
        String password=request.getParameter("password");
        Person person=personService.findByLogname(logname);
        if (person != null) {
            String p=person.getPassword();
            if (p.equals(password)) {
                request.getSession().setAttribute("person", person);
                request.getSession().setAttribute("perName", person.getName());
                request.getSession().setAttribute("name", logname);
                Integer event=personService.countPersonalTask(person.getRole(),person.getId());
                if(event==null){
                    event=0;
                }
                request.getSession().setAttribute("event", event);
                if (password.equals("123456")){
                    return "redirect:/content/change/password";
                }else {
                return "redirect:/content/index";}
            } else {
                map.put("message", "密码错误！");
                return "login"; }
        } else {
            map.put("message", "该用户不存在！");
            return "login";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,Map map){
        request.getSession().removeAttribute("name");
        request.getSession().removeAttribute("person");
        return "login";
    }
    @GetMapping("/content/change/password")
    public String changePage(HttpServletRequest request,Map map){
        Person person=(Person) request.getSession().getAttribute("person");
        if(person.getPassword().equals("123456")){
        map.put("changeMessage","初次登录请修改密码");}
        return "changePassword";
    }
    @PostMapping("/content/changePassword")
    public String changePassword(HttpServletRequest request,Map map){
        String password=(String)request.getParameter("newpassword");
        Person person=(Person)request.getSession().getAttribute("person");
        int id=person.getId();
        personService.changePassword(password,id);
        map.put("message","修改成功，请重新登陆！");
        return "login";

    }
}
