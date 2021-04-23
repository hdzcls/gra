package com.sju.graduation.service;

import com.sju.graduation.mapper.FilesLiuMapper;
import com.sju.graduation.pojo.FilesLiu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Service
public class FilesLiuService {
    @Autowired
    private FilesLiuMapper filesLiuMapper;
    public List<FilesLiu> findAllFiles(){
        return filesLiuMapper.findAllFiles();
    }
    public List<FilesLiu> findFilesByName(String name){
        String na="%"+name+"%";
        return filesLiuMapper.findFilesByName(na);
    }
    public void addFiles(FilesLiu filesLiu){
        filesLiuMapper.addFiles(filesLiu);
    }
    public void addTimes(String name){
        filesLiuMapper.addTimes(name);
    }
    public void deleteFiles(int id){
        filesLiuMapper.deleteFiles(id);
    }
    public Integer checkFiles(String name){
        return filesLiuMapper.checkFiles(name);
    }
    public String findFilesName(int id){
        return filesLiuMapper.findFilesName(id);
    }
}
