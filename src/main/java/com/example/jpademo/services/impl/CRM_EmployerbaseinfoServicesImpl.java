

package com.example.jpademo.services.impl;







import com.example.jpademo.common.LBK_Result;
import com.example.jpademo.entity.Employbaseinfo;
import com.example.jpademo.jpa.EmploybaseinfoRepository;
import com.example.jpademo.services.CRM_EmployerbaseinfoServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class CRM_EmployerbaseinfoServicesImpl implements CRM_EmployerbaseinfoServices {



    @Autowired
    private EmploybaseinfoRepository employbaseinfoRepository;


    @Override
    public Page<Employbaseinfo> chaxun(String page, String rows) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page)-1, Integer.parseInt(rows));
        Page<Employbaseinfo> all = employbaseinfoRepository.findAll(pageable);
        return all;
    }

    @Override
    public LBK_Result testnew(String name, String sex) {
        LBK_Result crm_result=null; //返回格式

        if (name.equals("")||sex.equals("")){
            crm_result=new LBK_Result(400,"输入为空",null);
        }else {
            crm_result=new LBK_Result(200,"下一步",null);
        }
        return  crm_result;
      /*  if (name.equals("")||sex.equals("")){
          crm_result=new CRM_Result(400,"输入不能为空",null);
        }
        else {
            crm_result=new CRM_Result(200,"输入有效，执行下一步操作",null);
        }
        return crm_result;*/
    }

    @Override
    public List<Employbaseinfo> selectById(Integer id) {
        List<Employbaseinfo> byId = employbaseinfoRepository.findByIdEquals(id);
        return byId;
    }



   /* @Transactional
    @Override
    public CRM_Result updatecategory(Category category) {
        int i = categoryRepository.updateByPrimaryKey(category);
        return CRM_Result.ok(i);
    }*/





}

























