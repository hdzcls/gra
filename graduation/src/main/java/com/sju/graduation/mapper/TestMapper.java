package com.sju.graduation.mapper;

import com.sju.graduation.pojo.Needs;
import com.sju.graduation.pojo.Test;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TestMapper {
    @Select("select test.*,(select name from person where id=pt.pid) as tester,(case pt.state when 2 then '已经结束' when 1 then '正在测试' else '未开始' end) as state  from test left join pt on test.id=pt.tid")
    public List<Test> findAllTest();
    @Select("select test.*,(select name from person where id=pt.pid) as tester,(case pt.state when 2 then '已经结束' when 1 then '正在测试' else '未开始' end) as state  from test left join pt on test.id=pt.tid where test.name like #{name}")
    public List<Test> findLikeTest(String name);
    @Select("select test.*,(select name from person where id=pt.pid) as tester,(case pt.state when 2 then '已经结束' when 1 then '正在测试' else '未开始' end) as state  from test left join pt on test.id=pt.tid where test.id=#{id}")
    public Test findTestById(int id);
    @Select("select id from test where name=#{name}")
    public Integer findIdByName(String name);
    @Select("select pid from pt where tid=#{tid}")
    public Integer findpidBytid(int tid);
    @Insert("insert into test(name,need_name,content,`explain`,writer) value(#{name},#{needName},#{content},#{explain},#{writer})")
    public void addTest(Test test);
    @Insert("insert into pt(pid,tid) value(#{pid},#{tid})")
    public void addpt(int pid,int tid);
    @Delete("delete from test where id=#{id}")
    public void deleteTest(int id);
    @Delete("delete from pt where tid=#{tid}")
    public void deletept(int id);
    @Update("update test set name=#{name},need_name=#{needName},content=#{content},`explain`=#{explain} where id=#{id}")
    public void updateTest(Test test);
    @Update("update pt set pid=#{pid} where tid=#{tid}")
    public void updatept(int pid,int tid);
    @Select("select ((select count(1) from pt where state=2)/(select count(1) from pt)*100) as count;")
    public Integer testSpeed();
    @Select("select count(1) from pt where state=0")
    public Integer testcount0();
    @Select("select count(1) from pt where state=1")
    public Integer testcount1();
    @Select("select count(1) from pt where state=2")
    public Integer testcount2();
    @Select("select test.*,(case pt.state when 2 then '已经结束' when 1 then '正在测试' else '未开始' end) as state from pt,test,needs,pn where pt.tid=test.id and test.need_name=needs.`name` and pn.nid=needs.id and  pt.state!=2 and pn.state=2 and pt.pid=#{id}")
    public List<Test> findPersonalTest(int id);
    @Select("select count(1) from pt,test,needs,pn where pt.tid=test.id and test.need_name=needs.`name` and pn.nid=needs.id and  pt.state!=2 and pn.state=2 and pt.pid=#{id}")
    public Integer countPersonalTest(int id);
    @Update("update pt set state=1 where tid=#{id}")
    public void startTest(int id);
    @Update("update pt set state=2 where tid=#{id}")
    public void endTest(int id);

}
