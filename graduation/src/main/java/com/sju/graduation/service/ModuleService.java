package com.sju.graduation.service;

import com.sju.graduation.mapper.ModuleMapper;
import com.sju.graduation.pojo.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService {
    @Autowired
    private ModuleMapper moduleMapper;
    public List<Module> finAllModule(){
        return moduleMapper.findAll();
    }
    public void deleteModule(int id){
        moduleMapper.deleteModule(id);
    }
    public void updateModule(Module module){
        moduleMapper.updateModule(module);
    }
    public List<Module> findLikeName(String name){
        String likeName="%"+name+"%";
        return moduleMapper.findLikeName(likeName);
    }
    public Module findById(int id){
        return moduleMapper.findById(id);
    }
    public void add(Module module){
        moduleMapper.add(module);
    }
    public Integer check(String name){
        return moduleMapper.checkByName(name);
    }
}
