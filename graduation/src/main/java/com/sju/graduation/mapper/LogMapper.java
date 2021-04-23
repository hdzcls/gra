package com.sju.graduation.mapper;

import com.sju.graduation.pojo.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LogMapper {
    @Select("select * from log order by id desc")
    public List<Log> findAll();
    @Insert("insert into log(date,person,action) values(#{date},#{person},#{action})")
    public void addLog(Log log);
    @Select("select * from log where person=#{person} order by id desc")
    public List<Log> findByPerson(String person);
}
