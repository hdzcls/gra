package com.sju.graduation.service;

import com.sju.graduation.mapper.PersonMapper;
import com.sju.graduation.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private NeedsService needsService;
    @Autowired
    private TestService testService;
    public Person findByLogname(String logname){
        return personMapper.findByLogname(logname);
    }
    public List<Person> findAll(int role){

        if(role==1){
            return personMapper.findAllDeveloper();
        }else {
            return personMapper.findAllTester();
        }
    }
    public void addPserson(Person person,int role){
        person.setRole(role);
        personMapper.addPerson(person);
    }
    public void deletePerson(int id){
        personMapper.deletepnPerson(id);
        personMapper.deleteptPerson(id);
        personMapper.deletePerson(id);
    }
    public void updatePerson(Person person){
        personMapper.updatePerson(person);
    }
    public List<Person> findByName(String uname,int role){
        String name="%"+uname+"%";
        return personMapper.findByname(name,role);
    }
    public Integer countPersonalTask(int role,int id){
        if (role==1){
            return needsService.countPersonalNeed(id);
        }else {
            return testService.countPersonalTest(id);
        }

    }
    public void startTask(int role,int id){
        if (role==1){
            needsService.startNeed(id);
        }else {
            testService.startTest(id);
        }

    }
    public void endTask(int role,int id){
        if (role==1){
            needsService.endNeed(id);
        }else {
            testService.endTest(id);
        }

    }
    public void changePassword(String password,int id){
        personMapper.changePassword(password,id);
    }
    public Person findById(int id){
        return personMapper.findById(id);
    }



}
