package com.sju.graduation.service;

import com.sju.graduation.mapper.NeedsMapper;
import com.sju.graduation.mapper.PersonMapper;
import com.sju.graduation.mapper.TestMapper;
import com.sju.graduation.pojo.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired
    private TestMapper testMapper;
    @Autowired
    private NeedsMapper needsMapper;
    @Autowired
    private PersonMapper personMapper;
    public List<Test> findAllTest(){
        return testMapper.findAllTest();
    }
    public List<Test> findLikeTest(String name){
        String n="%"+name+"%";
        return testMapper.findLikeTest(n);
    }
    public void deleteTest(int id){
        testMapper.deletept(id);
        testMapper.deleteTest(id);
    }
    public void addTest(Test test){
        testMapper.addTest(test);
        if(!test.getTester().equals("-1")){
        Integer tid=testMapper.findIdByName(test.getName());
        String tester=test.getTester();
        Integer pid=personMapper.findIdByName(tester);
        testMapper.addpt(pid,tid);
        }

    }
    public void updateTest(Test test){
        testMapper.updateTest(test);
        int tid=test.getId();
        int pid=personMapper.findIdByName(test.getTester());
        Integer id=testMapper.findpidBytid(tid);
        if(id!=null){
            testMapper.updatept(pid,tid);
        }else {
            testMapper.addpt(pid,tid);
        }

    }
    public Test findTestById(int id){
        return testMapper.findTestById(id);
    }
    public List<Test> findPersonalTest(int id){
        return testMapper.findPersonalTest(id);
    }
    public Integer countPersonalTest(int id){
        return testMapper.countPersonalTest(id);
    }
    public void startTest(int id){
        testMapper.startTest(id);
    }
    public void endTest(int id){
        testMapper.endTest(id);
    }
}
