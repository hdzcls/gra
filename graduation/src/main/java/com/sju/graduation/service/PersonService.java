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


}
