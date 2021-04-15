package com.sju.graduation.filter;

import com.sju.graduation.pojo.Person;
import com.sju.graduation.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(filterName = "请求过滤",urlPatterns = "/content/*")
public class PassFilter implements Filter {
    @Autowired
    private PersonService personService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        Person person=(Person) request.getSession().getAttribute("person");
        if (person==null){
            String contextPath = request.getContextPath();
            response.sendRedirect(request.getContextPath()+"/");
            //response.sendRedirect("www.baidu.com");
        }else {
        Integer event=personService.countPersonalTask(person.getRole(),person.getId());
        if(event==null){
            event=0;
        }
        request.getSession().setAttribute("event", event);
        }
        filterChain.doFilter(request,response);


    }
}
