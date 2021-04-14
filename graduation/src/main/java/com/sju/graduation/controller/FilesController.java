package com.sju.graduation.controller;

import com.sju.graduation.pojo.FilesLiu;
import com.sju.graduation.service.FilesLiuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class FilesController {
    @Autowired
    private FilesLiuService filesLiuService;
    @GetMapping("/content/files")
    public String files(){
        //return "files";
        return "files";
    }

    @RequestMapping("/content/files/deleteFiles")
    @ResponseBody
    public boolean deleteFiles(int id){
        filesLiuService.deleteFiles(id);
        return true;
    }

    @PostMapping("/content/files/checkFiles")
    @ResponseBody
    public boolean checkFiles(HttpServletRequest request){
        String name=request.getParameter("name");
        if (name!=null){
            int in=name.lastIndexOf("\\");
            String na=name.substring(in+1,name.length());
            Integer flag=filesLiuService.checkFiles(na);
            if (flag==0){
                return true;
            }else {
                return false;
            }
        }
        return true;

    }

    @RequestMapping("/content/files/findAllFiles")
    @ResponseBody
    public List<FilesLiu> findAllFiles(){
        return filesLiuService.findAllFiles();
    }
    @RequestMapping("/content/files/findFilesByName")
    @ResponseBody
    public List<FilesLiu> findFilesByName(String name){
        return filesLiuService.findFilesByName(name);
    }
    @RequestMapping("/content/files/fileUpload")
    public String fileUpload(@RequestParam("fileName") MultipartFile file, HttpServletRequest request){
        if(file.isEmpty()){
            return "false";
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);

        String path = "D:/test" ;
        File dest = new File(path + "/" + fileName);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            FilesLiu filesLiu=new FilesLiu();
            filesLiu.setName(fileName);
            String name=(String)request.getSession().getAttribute("name");
            filesLiu.setWriter(name);
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            filesLiu.setDate(dateFormat.format(new Date()));
            filesLiuService.addFiles(filesLiu);
            return "redirect:/content/files";
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        }
    }
    @RequestMapping("/content/files/download")
    public String downLoad(String name,HttpServletResponse response) throws UnsupportedEncodingException {
        String filename=name;
        String filePath = "D:/test" ;
        File file = new File(filePath + "/" + filename);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            // response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" +   java.net.URLEncoder.encode(filename,"UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download---" + filename);
            filesLiuService.addTimes(name);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }
}
