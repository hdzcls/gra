package com.sju.graduation.mapper;

import com.sju.graduation.pojo.Module;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ModuleMapper {
    @Select("select *,(select count(1) from needs where needs.module=module.name) as count,(select count(1) from needs,pn where needs.id=pn.nid and needs.module=module.name and pn.state=2) as end from module")
    public List<Module> findAll();
    @Select("select *,(select count(1) from needs where needs.module=module.name) as count,(select count(1) from needs,pn where needs.id=pn.nid and needs.module=module.name and pn.state=2) as end from module where name like #{name}")
    public List<Module> findLikeName(String name);
    @Delete("delete from module where id=#{id}")
    public void deleteModule(int id);
    @Update("update module set name=#{name},content=#{content} where id=#{id}")
    public void updateModule(Module module);
    @Select("select * from module where id=#{id}")
    public Module findById(int id);
    @Select("select count(1) from module where name=#{name}")
    public Integer checkByName(String name);
    @Insert("insert into module(name,content,writer) values(#{name},#{content},#{writer})")
    public void add(Module module);
}
