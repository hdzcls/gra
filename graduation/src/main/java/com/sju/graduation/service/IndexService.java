package com.sju.graduation.service;

import com.sju.graduation.mapper.NeedsMapper;
import com.sju.graduation.mapper.PersonMapper;
import com.sju.graduation.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class IndexService {
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private NeedsMapper needsMapper;
    @Autowired
    private TestMapper testMapper;
    public List countPerson(){
        Integer developer=personMapper.countDeveloper();
        Integer tester=personMapper.countTester();
        List list=new LinkedList<>();
        Map map1=new HashMap();
        map1.put("value",developer);
        map1.put("name","开发人员");
        Map map2=new HashMap();
        map2.put("value",tester);
        map2.put("name","测试人员");
        list.add(map1);
        list.add(map2);
        return list;
    }
    public Integer developSpeed(){
        return needsMapper.developSpeed();
    }
    public Integer testSpeed(){
        return testMapper.testSpeed();
    }
    public List<int[]> listAll(){
        List list=new LinkedList<>();

        int[] all0={testMapper.testcount0(),needsMapper.needscount0()};
        int[] all1={testMapper.testcount1(),needsMapper.needscount1()};
        int[] all2={testMapper.testcount2(),needsMapper.needscount2()};
        list.add(all0);
        list.add(all1);
        list.add(all2);
        return list;
    }
}
