package com.example.jpademo.controller;



import com.example.jpademo.common.CRM_Result;
import com.example.jpademo.entity.Cart;
import com.example.jpademo.entity.Category;
import com.example.jpademo.entity.Employbaseinfo;
import com.example.jpademo.services.CRM_EmployerbaseinfoServices;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/Employerbaseinfo")
public class CRM_EmployerbaseinfoController {

    @Autowired
    private CRM_EmployerbaseinfoServices crm_employerbaseinfoServices;

    @RequestMapping(value = "/importEmp", method = RequestMethod.POST)
    public Object daor(MultipartFile filet) {
        return crm_employerbaseinfoServices.daor(filet);
    }

    @RequestMapping(value = "/exporEmp")
    public Object exporEmp(HttpServletResponse response) { return crm_employerbaseinfoServices.printEmpinfo(response); }


    @PostMapping(value = "/testupdate")
    public CRM_Result testfenye(Cart cart) {
        return crm_employerbaseinfoServices.update(cart);
    }

    @PostMapping(value = "/testupdatecategory")
    public CRM_Result testupdatecategory(Category category) {
        return crm_employerbaseinfoServices.updatecategory(category);
    }

    @RequestMapping(value = "/selectemp")
    public CRM_Result tselectemp() {
        return crm_employerbaseinfoServices.selectEmp();
    }


    @PostMapping(value = "/updateEmp")
    public CRM_Result updateEmp(Employbaseinfo employbaseinfo) {
        return crm_employerbaseinfoServices.updateEmp(employbaseinfo);
    }

    /*
    *//*查询所有员工*//*
    @RequestMapping(value = "/testUpdate")
    public CRM_Result testUpdate() {
        return crm_employerbaseinfoServices.findallEmpers();
    }

    *//*测试分页查询*//*
    @RequestMapping(value = "/testfeny")
    public CRM_Result testfenye() {
        return crm_employerbaseinfoServices.chaxun("1", "4");
    }

    *//*测试修改查询*//*
    @RequestMapping(value = "/updateaa")
    public CRM_Result updateaa(Employbaseinfo employbaseinfo) {
        return crm_employerbaseinfoServices.update(employbaseinfo);
    }*/

}
















   /* @PostMapping(value = "/newupdate")
    public CRM_Result newupdate(String employername,String sex,String remark,String employerid){//根据token获取用户id
        if (employername != null && employername.length()!=0 && remark.length()!= 0) return crm_employerbaseinfoServices.testnewchaxun(employername, sex, remark, Integer.parseInt(employerid));
        //&&sex != null && sex.length()!= 0&&remark != null
        return CRM_Result.build(400,"输入不为空");
    }*/

/*

    @RequestMapping("/testss")
    public Object testss(){
        Object testchxun = crm_employerbaseinfoServices.testchxun();
        return testchxun;
    }


    @PostMapping(value = "/chaxun")
    public Object chaxun(String ids){
        String str_arr[]=ids.split(",");
        List<Ceshilei> list2=new ArrayList<>();
        for (int z = 0; z <str_arr.length ; z++) {
            List<Object> chaxun = crm_employerbaseinfoServices.chaxun(Integer.parseInt(str_arr[z]));
            for (int j=0;j<chaxun.size();j++){
                Ceshilei ceshilei=new Ceshilei();
                Object[] obj = (Object[])chaxun.get(j);
                ceshilei.setEmployername(obj[0].toString());
                ceshilei.setSex(obj[1].toString());
                ceshilei.setPhone(obj[2].toString());
                ceshilei.setQualifications(obj[3].toString());
                ceshilei.setPwd(obj[4].toString());
                ceshilei.setParent_phone(obj[5].toString());
                list2.add(ceshilei);
                break;
            }
        }
       return CRM_Result.ok(list2);
    }





    @PostMapping(value = "/selcetEmployerByIdOrName")
    public CRM_Result selcetEmployerByIdOrName(String departmentname,String id,String name){
        CRM_Result crm_result = crm_employerbaseinfoServices.selcetEmployerByIdOrName(departmentname, id, name);
        return crm_result;
    }

    @PostMapping(value = "/selcetEmployer")
    public CRM_resultt selcetEmployer(String page, String limit){
        Page<CRM_Employerbaseinfo> crm_employerbaseinfos = crm_employerbaseinfoServices.selcetEmployer(page, limit);
        return CRM_resultt.build(200, "查询成功", (int) crm_employerbaseinfos.getTotalElements(), crm_employerbaseinfos.getContent());
    }


    @PostMapping(value = "/addallEmployerinfo")
    public CRM_Result addallEmployerinfo(CRM_Employerbaseinfo crm_employerbaseinfo, CRM_Employparticularinfo crm_employparticularinfo){
        CRM_Result crm_result = crm_employerbaseinfoServices.addallEmployerinfo(crm_employerbaseinfo, crm_employparticularinfo);
        return crm_result;
    }


    @RequestMapping("/select")
    public CRM_Result addEmployerbaseinfo(){
        CRM_Result crm_result = crm_employerbaseinfoServices.selcetEmployerbaseinfo();
        return crm_result;
    }

   @PostMapping(value = "/add")
    public CRM_Result add(String employer_name, String sex, String jointime, String phone, String card_id, String remark, String departmentname) {
       System.out.println(employer_name);
       CRM_Result crm_result = crm_employerbaseinfoServices.addEmployerbaseinfo(employer_name, sex, jointime, phone, card_id, remark, departmentname);
       return  crm_result;
   }

    @PostMapping(value = "/newadd")
    public CRM_Result newadd(CRM_Employerbaseinfo crm_employerbaseinfo) {
        CRM_Result crm_result = crm_employerbaseinfoServices.newaddEmployerbaseinfo(crm_employerbaseinfo);
        return  crm_result;
    }


   @PostMapping(value = "/getEmployer")
        public CRM_Result getEmployer(String phone){
       System.out.println(phone);
       CRM_Result crm_result = crm_employerbaseinfoServices.selectEmployerByphone(phone);
       return crm_result;
   }

    @PostMapping(value = "/getAllEmployers")
    public CRM_Result getAllEmployers(String sex){
        System.out.println(sex);
        CRM_Result crm_result = crm_employerbaseinfoServices.selectEmployerBysex(sex);
        return crm_result;
    }

    @PostMapping(value = "/selectEmployers")
    public CRM_Result selectEmployers(String departmentname, String content, String type){
        CRM_Result crm_result = crm_employerbaseinfoServices.selectEmployerinfo(departmentname, content, type);
        return crm_result;
    }

}
*/
