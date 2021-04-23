package com.sju.graduation.mapper;

import com.sju.graduation.pojo.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProjectMapper {
    @Select("select * from project where id=1")
    public Project findProject();
    @Update("update project set name=#{name},start=#{start},end=#{end},target=#{target} where id=1")
    public void updateProject(Project project);
}
