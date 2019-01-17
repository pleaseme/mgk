package com.example.jpademo.services;


import com.example.jpademo.common.LBK_Result;
import com.example.jpademo.entity.Employbaseinfo;
import org.springframework.data.domain.Page;

import java.util.List;


public interface CRM_EmployerbaseinfoServices {


  //完善方法
  LBK_Result testnew(String name, String sex);



  List<Employbaseinfo> selectById(Integer id);


    //分页查询
    Page<Employbaseinfo> chaxun(String page, String rows);










}
