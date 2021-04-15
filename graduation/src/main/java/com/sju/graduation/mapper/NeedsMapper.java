package com.sju.graduation.mapper;

import com.sju.graduation.pojo.Needs;
import com.sju.graduation.pojo.Test;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NeedsMapper {
    @Select("select needs.*,(select name from person where id=pn.pid) as developer,(case pn.state when 2 then '已经结束' when 1 then '正在开发' else '未开始' end) as state from needs left join pn on needs.id=pn.nid")
    public List<Needs> findAllNeeds();
    @Select("select needs.*,(select name from person where id=pn.pid) as developer,(case pn.state when 2 then '已经结束' when 1 then '正在开发' else '未开始' end) as state from needs left join pn on needs.id=pn.nid where needs.name like #{name}")
    public List<Needs> findNeedsByName(String name);
    @Select("select needs.*,(select name from person where id=pn.pid) as developer,(case pn.state when 2 then '已经结束' when 1 then '正在开发' else '未开始' end) as state from needs left join pn on needs.id=pn.nid where needs.id=#{id}")
    public Needs findNeedsById(int id);
    @Delete("delete from needs where id=#{id}")
    public void deleteNeedsById(int Id);
    @Delete("delete from pn where nid=#{id}")
    public void deletepnById(int Id);
    @Insert("insert into needs(name,module,function,`explain`,writer) values(#{name},#{module},#{function},#{explain},#{writer})")
    public void addNeeds(Needs needs);
    @Insert("insert into pn(pid,nid) value(#{pid},#{nid})")
    public void addpn(int pid,int nid);
    @Select("select id from needs where name=#{name}")
    public Integer findId(String name);
    @Update("update needs set name=#{name},module=#{module},function=#{function},`explain`=#{explain} where id=#{id}")
    public void updateNeeds(Needs needs);
    @Update("update pn set pid=${pid} where nid=#{nid}")
    public void updatenp(int pid,int nid);
    @Select("select pid from pn where nid=#{nid}")
    public Integer findpnBynid(int nid);
    @Select("select ((select count(1) from pn where state=2)/(select count(1) from pn)*100) as count;")
    public Integer developSpeed();
    @Select("select count(1) from pn where state=0")
    public Integer needscount0();
    @Select("select count(1) from pn where state=1")
    public Integer needscount1();
    @Select("select count(1) from pn where state=2")
    public Integer needscount2();
    @Select("select needs.*,pn.state from pn,needs where pn.nid=needs.id and pn.state!=2 and pn.pid=#{id}")
    public List<Needs> findPersonalNeed(int id);
    @Select("select count(1) from pn,needs where pn.nid=needs.id and pn.state!=2 and pn.pid=#{id}")
    public Integer countPersonalNeed(int id);
    @Update("update pn set state=1 where nid=#{id}")
    public void startNeed(int id);
    @Update("update pn set state=2 where nid=#{id}")
    public void endNeed(int id);
}
