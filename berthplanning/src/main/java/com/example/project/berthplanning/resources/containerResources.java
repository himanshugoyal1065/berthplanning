package com.example.project.berthplanning.resources;
import com.example.project.berthplanning.business.baplieParser;
import com.example.project.berthplanning.model.container;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.POST;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/rest")
public class containerResources {
   public baplieParser main;
   public File fileAngular;
/*   @PostMapping("/file")
   public ResponseEntity<String> getPostFile(MultipartFile file){
       System.out.println("In getpostfile");
       try {
           FileInputStream fs=(FileInputStream) file.getInputStream();
           int c;
           fileAngular = new File(file.getOriginalFilename());
           fileAngular.createNewFile();
           FileOutputStream fos = new FileOutputStream(fileAngular);
           while ((c = fs.read()) != -1) {
               fos.write(file.getBytes());
           }
       }catch (Exception e){
           System.out.println(e);
       }
       String message = "You successfully uploaded " + file.getOriginalFilename() + "!";
       return ResponseEntity.status(HttpStatus.OK).body(message);
   }*/
@PostMapping("/file")
public void getFile(MultipartFile file){
    FileInputStream ins = null;
    FileOutputStream outs = null;
    System.out.println("file method");
    try {
//File infile =new File("C:\\Users\\TutorialsPoint7\\Desktop\\abc.txt");
        File outfile =new File("baplieFromJava.txt");
        ins =(FileInputStream)file.getInputStream();
        outs = new FileOutputStream(outfile);
        byte[] buffer = new byte[1024];
        int length;

        while ((length = ins.read(buffer)) > 0) {
            outs.write(buffer, 0, length);
        }
        ins.close();
        outs.close();
        System.out.println("File copied successfully!!");
    } catch(IOException ioe) {
        ioe.printStackTrace();
    }
}
   @PostMapping("/getfile")
   public ResponseEntity<String> multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException
   {
       String message="";
       try {
           System.out.println("In multiparttofile");
           FileInputStream fs=(FileInputStream) multipart.getInputStream();
           System.out.println(multipart);

           fileAngular = new File(multipart.getOriginalFilename());
           fileAngular.createNewFile();
           FileOutputStream fos = new FileOutputStream(fileAngular);
           fos.write(multipart.getBytes());
           fos.close();
           System.out.println(fileAngular);
           message = "You successfully uploaded !";
       }
       catch(Exception e)
       {
           System.out.println(e);
       }
           return ResponseEntity.status(HttpStatus.OK).body(message);



   }
  /* @PostMapping("/file1")
   public void getFile(MultipartFile file1){
       System.out.println("I am here!");
       file=(File) file1;
       System.out.println(file1);
       System.out.println(file);
   }*/
    @GetMapping("/containers/{PORT_OF_DISCHARGE}")
    public List<container> getContainers(@PathVariable("PORT_OF_DISCHARGE") final String POD)
    {
        main = new baplieParser();
        main.BAPLIE_EQUIPMENT="CN";
        //main.file = new File("C:\\Users\\jainmo\\Downloads\\baplie With_2000Container01.edi");
       // main.file=fileAngular;
        List<container> cont =  main.getContainers(POD);
        return cont;
    }
    @GetMapping("/containers")
    public Map<String,String> getAll()
    {
        main = new baplieParser();
        main.BAPLIE_EQUIPMENT="CN";
        main.file = new File("C:\\Users\\jainmo\\Downloads\\berthplanning\\baplieFromJava.txt");
       // main.file = fileAngular;
        Map<container,String> mappings = new HashMap<>();
        mappings = main.getAll();
        Map<String,String> finalVal = new HashMap<>();
        for(Map.Entry<container,String> value : mappings.entrySet())
        {
            String containerId = value.getKey().getcId();
            finalVal.put(containerId,value.getValue());
        }
        return finalVal;
    }
    @GetMapping("/getcount")
    public Map<String,Integer> getCount()
    {
        main = new baplieParser();
        main.BAPLIE_EQUIPMENT="CN";
        main.file = new File("C:\\Users\\jainmo\\Downloads\\berthplanning\\baplieFromJava.txt");
        Map<String,Integer> mappings = new HashMap<>();
        mappings = main.getCount();
        return mappings;
    }
    @GetMapping("/getdate")
    public Map<String,String> getDate()
    {
        main = new baplieParser();
        main.BAPLIE_EQUIPMENT="CN";
        main.file = new File("C:\\Users\\jainmo\\Downloads\\berthplanning\\baplieFromJava.txt");
        Map<String,String> map = new HashMap<>();
        map.put("YYMMDDHHMM",main.getDate());
        return map;
    }
    @GetMapping("/portcount/{PORT_OF_DISCHARGE}")
    public Map<String,Integer> getcountport(@PathVariable("PORT_OF_DISCHARGE") final String POD)
    {
        main = new baplieParser();
        main.BAPLIE_EQUIPMENT="CN";
        main.file = new File("C:\\Users\\jainmo\\Downloads\\berthplanning\\baplieFromJava.txt");
        Map<String,Integer> mappings = new HashMap<>();
        mappings =  main.getPortCount(POD);
        return mappings;
    }
}
