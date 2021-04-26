package com.sju.graduation.service;

import com.sju.graduation.mapper.LogMapper;
import com.sju.graduation.pojo.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class LogService {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LogMapper logMapper;
    public List<Log> findAllLog(){
        return logMapper.findAll();
    }
    public void insertLog(String action){
        Log log=new Log();
        String name=(String)request.getSession().getAttribute("perName");
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        log.setPerson(name);
        log.setAction(action);
        log.setDate(dateFormat.format(new Date()));
        logMapper.addLog(log);
    }
    public List<Log> findByPerson(String name){
        String na="%"+name+"%";
        return logMapper.findByPerson(na);
    }
}
