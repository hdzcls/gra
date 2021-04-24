package com.sju.graduation.service;

import com.sju.graduation.mapper.ProjectMapper;
import com.sju.graduation.pojo.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectSevice {
    @Autowired
    private ProjectMapper projectMapper;
    public Project findProject(){
        return projectMapper.findProject();
    }
    public void update(Project project){
        projectMapper.updateProject(project);
    }
}
