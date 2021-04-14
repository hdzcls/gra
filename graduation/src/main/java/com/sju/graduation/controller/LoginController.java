package com.sju.graduation.controller;

import com.sju.graduation.mapper.PersonMapper;
import com.sju.graduation.pojo.Person;
import com.sju.graduation.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
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
                request.getSession().setAttribute("name", logname);
                request.getSession().setAttribute("event", 3);
                return "redirect:/content/index";
            } else {
                map.put("message", "密码错误！");
                return "login";

            }
        } else {
            map.put("message", "该用户不存在！");
            return "login";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("name");
        request.getSession().removeAttribute("person");
        return "login";
    }
}
