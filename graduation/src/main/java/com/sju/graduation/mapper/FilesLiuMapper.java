package com.sju.graduation.mapper;

import com.sju.graduation.pojo.FilesLiu;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FilesLiuMapper {
    @Select("select * from files")
    public List<FilesLiu> findAllFiles();
    @Select("select count(1) from files where name=#{name}")
    public Integer checkFiles(String name);
    @Select("select * from files where name like #{name}")
    public List<FilesLiu> findFilesByName(String name);
    @Insert("Insert into files(name,date,writer) values(#{name},#{date},#{writer})")
    public void addFiles(FilesLiu filesLiu);
    @Update("update files set times=(times+1) where name=#{name}")
    public void addTimes(String name);
    @Delete("delete from files where id=#{id}")
    public void deleteFiles(int id);

}
