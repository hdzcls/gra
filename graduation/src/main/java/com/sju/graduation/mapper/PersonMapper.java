package com.sju.graduation.mapper;

import com.sju.graduation.pojo.Person;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PersonMapper {
    @Select("select count(1) from person where role=1")
    public Integer countDeveloper();
    @Select("select count(1) from person where role=2")
    public Integer countTester();
    @Select("select * from person where logname=#{logname}")
    public Person findByLogname(String logname);
    @Select("select id from person where name=#{name}")
    public Integer findIdByName(String name);
    @Select("select * from person where name like #{name} and role=#{role}")
    public List<Person> findByname(String name,int role);
    @Select("select *,(select count(*) from pn where pid=p.id and state!=2)as task from person p where role=1")
    public List<Person> findAllDeveloper();
    @Select("select *,(select count(*) from pt where pid=p.id and state!=2)as task from person p where role=2")
    public List<Person> findAllTester();
    @Insert("insert into person(name,age,phone,logname,workage,role) values(#{name},#{age},#{phone},#{logname},#{workage},#{role})")
    public void addPerson(Person person);
    @Delete("delete from person where id=#{id}")
    public void deletePerson(int id);
    @Delete("delete from pn where pid=#{id}")
    public void deletepnPerson(int id);
    @Delete("delete from pt where pid=#{id}")
    public void deleteptPerson(int id);
    @Update("update person set name=#{name},age=#{age},phone=#{phone},workage=#{workage} where id=#{id}")
    public void updatePerson(Person person);

}
