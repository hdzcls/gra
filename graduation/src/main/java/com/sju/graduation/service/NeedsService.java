package com.sju.graduation.service;

import com.sju.graduation.mapper.NeedsMapper;
import com.sju.graduation.mapper.PersonMapper;
import com.sju.graduation.pojo.Needs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NeedsService {
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private NeedsMapper needsMapper;
    public List<Needs> findAllNeeds(){
        return needsMapper.findAllNeeds();
    }
    public List<Needs> findNeedsByName(String name){
        String na="%"+name+"%";
        return needsMapper.findNeedsByName(na);
    }
    public void deleteNeeds(int id){
        needsMapper.deletepnById(id);
        needsMapper.deleteNeedsById(id);
    }
    public void addNeeds(Needs needs){
        needsMapper.addNeeds(needs);
        String pname=needs.getDeveloper();
        if(!pname.equals("-1")){
            Integer nid=needsMapper.findId(needs.getName());
            Integer pid=personMapper.findIdByName(pname);
            needsMapper.addpn(pid,nid);
        }
    }
    public Needs findNeedById(int id){
        return needsMapper.findNeedsById(id);
    }
    public void updateNeeds(Needs needs){
        needsMapper.updateNeeds(needs);
        int nid=needs.getId();
        Integer pid=needsMapper.findpnBynid(nid);
        String dev=needs.getDeveloper();
        if (!dev.equals("")){
        Integer id=personMapper.findIdByName(needs.getDeveloper());
        if (pid!=null){
            needsMapper.updatenp(id,nid);
        }else {
            needsMapper.addpn(id,nid);
        }
        }

    }
    public List<Needs> findPersonalNeed(int id){
        return needsMapper.findPersonalNeed(id);
    }
    public Integer countPersonalNeed(int id){
        return needsMapper.countPersonalNeed(id);
    }
    public void startNeed(int id){
        needsMapper.startNeed(id);
    }
    public void endNeed(int id){
        needsMapper.endNeed(id);
    }


}
