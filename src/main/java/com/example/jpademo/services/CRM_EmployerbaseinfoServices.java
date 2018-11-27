package com.example.jpademo.services;

import com.example.jpademo.common.CRM_Result;
import com.example.jpademo.entity.Cart;
import com.example.jpademo.entity.Category;
import com.example.jpademo.entity.Employbaseinfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;



public interface CRM_EmployerbaseinfoServices {


    //导入员工信息
    Object daor(MultipartFile file);

    /*
    * 打印员工信息
    * */
    Object printEmpinfo(HttpServletResponse response);

    //测试方法
  //  CRM_Result newtest(Employbaseinfo employbaseinfo);

    //查询所有员工
   // CRM_Result findallEmpers();

    //分页查询
   // CRM_Result chaxun(String page,String rows);

    //修改Cart信息
    CRM_Result update(Cart cart);

    //修改category信息
    CRM_Result updatecategory(Category category);

    //查询员工信息
    CRM_Result selectEmp();

    //修改员工信息
    CRM_Result updateEmp(Employbaseinfo employbaseinfo);































    //测试updateJPA
   //CRM_Result testUpdate(CRM_Employerbaseinfo crm_employerbaseinfo);


    //测试新的修改方法
  // CRM_Result testnewchaxun(String employername,String sex,String remark,int employerid);


    //测试联合查询
   // Object testchxun();

   //测试联合查询
   // List<Object> chaxun(int id);


     //根据部门，工号或者姓名查询
   // CRM_Result selcetEmployerByIdOrName(String departmentname,String id,String name);

    //所有员工列表
   // Page<CRM_Employerbaseinfo> selcetEmployer(String page, String size);

    //查询所有员工信息
   // CRM_Result selcetEmployerbaseinfo();

    //根据手机号查询员工
  //  CRM_Result selectEmployerByphone(String phone);

    //查询所有的男员工或则女员工
   // CRM_Result selectEmployerBysex(String sex);

    //添加员工信息
  //  CRM_Result addEmployerbaseinfo(String employer_name, String sex, String jointime, String phone, String card_id, String remark, String departmentname);

    //新的添加员工信息
    //CRM_Result newaddEmployerbaseinfo(CRM_Employerbaseinfo crm_employerbaseinfo);

    //添加员工具体信息
   // CRM_Result addEmployparticularinfo(String phone,String qualifications,String ismarry,String birthday,String parentphone,String natives,String address,String interesting,String certificate,String pic,String ill,String fixedphone,String learnship,String state,String pwd);

    //新的添加员工的具体信息
   // CRM_Result addEmployparticularinfo(CRM_Employparticularinfo crm_employparticularinfo);

    //按照查询条件进行查询
  //  CRM_Result selectEmployerinfo(String departmentname,String content,String type);

      //添加员工所有信息
   // CRM_Result addallEmployerinfo(CRM_Employerbaseinfo crm_employerbaseinfo,CRM_Employparticularinfo crm_employparticularinfo);

    //导出员工信息
   // CRM_Result exportEmployerbaseinfo();



}
