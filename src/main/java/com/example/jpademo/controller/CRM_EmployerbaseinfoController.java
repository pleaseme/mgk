package com.example.jpademo.controller;




import com.example.jpademo.common.LBK_Result;
import com.example.jpademo.entity.Employbaseinfo;
import com.example.jpademo.services.CRM_EmployerbaseinfoServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/Employerbaseinfo")
public class CRM_EmployerbaseinfoController {


    /*
    * 测试缓存
    * */
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;


    @Autowired
    private CRM_EmployerbaseinfoServices crm_employerbaseinfoServices;


    /**
     * 测试token*/
    @PostMapping(value = "/token")
    public LBK_Result testneew(String name, HttpServletRequest request) {
       return LBK_Result.ok(name+request.getHeader("token"));
    }

/**
 * 完善方法*/
@RequestMapping(value = "/testneew")
public LBK_Result testneew(String page,String size) {
    if (page==null&&size==null) {

        Page<Employbaseinfo> chaxun = crm_employerbaseinfoServices.chaxun("1", "4");
        return LBK_Result.ok(chaxun.getContent());
    }else {
        return null;
    }

}


    /**
     * c测试缓存*/
    @RequestMapping("/g")
    public   Object g(){
        String emapa="aaaaaaa";
        RedisSerializer redisSerializer = new StringRedisSerializer();//序列化
        redisTemplate.setKeySerializer(redisSerializer);
      List<Employbaseinfo> list= (List<Employbaseinfo>) redisTemplate.opsForValue().get(emapa);
      if (null==list){
          List<Employbaseinfo> employbaseinfos = crm_employerbaseinfoServices.selectById(4);
          redisTemplate.opsForValue().set(emapa,employbaseinfos);
          System.out.println(123);
      }else {
          System.out.println(666);
         List<Employbaseinfo> list1= (List<Employbaseinfo>) redisTemplate.opsForValue().get(emapa);
          System.out.println(list1.get(0).getId()+list1.get(0).getEmployerName());
      }
        return  "成功";
    }









}

