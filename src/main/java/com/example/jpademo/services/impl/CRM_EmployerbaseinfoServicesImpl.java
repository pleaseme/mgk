

package com.example.jpademo.services.impl;




import com.example.jpademo.common.CRM_Result;


import com.example.jpademo.entity.Cart;
import com.example.jpademo.entity.Category;
import com.example.jpademo.entity.Employbaseinfo;
import com.example.jpademo.jpa.CartRepository;
import com.example.jpademo.jpa.CategoryRepository;
import com.example.jpademo.jpa.EmploybaseinfoRepository;
import com.example.jpademo.services.CRM_EmployerbaseinfoServices;
import com.example.jpademo.utils.ExportUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class CRM_EmployerbaseinfoServicesImpl implements CRM_EmployerbaseinfoServices {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EmploybaseinfoRepository employbaseinfoRepository;


   /* @Override
    public CRM_Result newtest(Employbaseinfo employbaseinfo) {
        List<Employbaseinfo> byName = employbaseinfoRepository.findByName(employbaseinfo);
        return CRM_Result.ok(byName);
    }*/

    @Override
    public Object daor(MultipartFile file) {
        if (file==null) {
            return ("file不能为空");
        }
        List<Employbaseinfo> HeroList = new ArrayList();
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
            HSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++)
            {
                Row row = sheet.getRow(i);//获取索引为i的行，以0开始

                System.out.println(row.getCell(0).getNumericCellValue());
                System.out.println(row.getCell(1).getStringCellValue());
                System.out.println(row.getCell(2).getStringCellValue());
                System.out.println(row.getCell(3).getNumericCellValue());
                System.out.println((int) row.getCell(4).getNumericCellValue());
                System.out.println(row.getCell(5).getNumericCellValue());
                System.out.println((row.getCell(6).getStringCellValue()));
                System.out.println(row.getCell(7).getStringCellValue());

                Employbaseinfo employbaseinfo = null;
                employbaseinfo = new Employbaseinfo();
                employbaseinfo.setEmployerId((int) row.getCell(0).getNumericCellValue());
                employbaseinfo.setEmployerName(row.getCell(1).getStringCellValue());
                employbaseinfo.setSex(row.getCell(2).getStringCellValue());
                employbaseinfo.setJointime(String.valueOf(row.getCell(3).getNumericCellValue()));
                employbaseinfo.setPhone(String.valueOf(row.getCell(4).getNumericCellValue()));
                employbaseinfo.setCardId(String.valueOf(row.getCell(5).getNumericCellValue()));
                employbaseinfo.setRemark(row.getCell(6).getStringCellValue());
                employbaseinfo.setDepartmentname(row.getCell(7).getStringCellValue());
                employbaseinfo.setCreateTime(new Date());
                employbaseinfo.setUpdateTime(new Date());
                HeroList.add(employbaseinfo);
                employbaseinfoRepository.save(employbaseinfo);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object printEmpinfo (HttpServletResponse response){
        List<Object> select = categoryRepository.select();
        String title = "员工信息表";
        String[] headers = {"工号", "姓名", "入职时间", "电话号码", "身份证"};
        ExportUtils exportUtils = new ExportUtils();
        try {
            Object o = exportUtils.exportInfo(response, select, title, headers);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Transactional
    @Override
    public CRM_Result update(Cart cart) {
        int i = cartRepository.updateByPrimaryKeySelective(cart);
        return CRM_Result.ok(i);
    }

    @Transactional
    @Override
    public CRM_Result updatecategory(Category category) {
        int i = categoryRepository.updateByPrimaryKey(category);
        return CRM_Result.ok(i);
    }

    @Override
    public CRM_Result selectEmp() {
        List<Employbaseinfo> all = employbaseinfoRepository.findAll();
        return CRM_Result.ok(all);
    }

    @Transactional
    @Override
    public CRM_Result updateEmp(Employbaseinfo employbaseinfo) {
        int update = employbaseinfoRepository.update(employbaseinfo);
        return CRM_Result.ok(update);
    }


}








   /* @Override
    public CRM_Result findallEmpers() {
        List<Employbaseinfo> all = crm_employerbaseinfoJPA.findAll();
        return CRM_Result.ok(all);
    }

    @Override
    public CRM_Result chaxun(String page, String rows) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page)-1, Integer.parseInt(rows));
        Page<Employbaseinfo> all = crm_employerbaseinfoJPA.findAll(pageable);
        return CRM_Result.ok(all.getContent());
    }

    @Override
    public CRM_Result update(Employbaseinfo employbaseinfo) {
        int i = crm_employerbaseinfoJPA.updateByPrimaryKeySelective(employbaseinfo);
        return CRM_Result.ok(i);
    }*/

















/*
@Transactional         //开启事务（运行报错实行回滚）
    @Override
    public CRM_Result testUpdate(CRM_Employerbaseinfo crm_employerbaseinfo) {
        int update = crm_employerbaseinfoJPA.update(crm_employerbaseinfo);
        return CRM_Result.ok(update);
    }


 @Transactional         //开启事务（运行报错实行回滚）
    @Override
    public CRM_Result testnewchaxun(String employername,String sex,String remark,int employerid) {
        int i = crm_employerbaseinfoJPA.updateByPrimaryKeySelective(employername, sex, remark, employerid);
        System.out.println(i);
        return CRM_Result.ok();
    }


 @Override
    public Object testchxun() {         //测试联合查询
        List<Object> testseclect = crm_employerbaseinfoJPA.testseclect();
        for (int i = 0; i <testseclect.size() ; i++) {
            Object[] obj = (Object[])testseclect.get(i);
            System.out.println(obj[0].toString());
            System.out.println(obj[1].toString());
            System.out.println(obj[2].toString());
            System.out.println(obj[3].toString());
        }
        return testseclect;
    }

    @Override
    public List<Object> chaxun(int id) {
        List<Object> chaxun = crm_employerbaseinfoJPA.chaxun(id);
        return chaxun;
    }


    @Override
    public CRM_Result selcetEmployerByIdOrName(String departmentname, String id, String name) {
        if((id==null||id.equals(""))&&(name != null && name.length() != 0) ){  //工号为空，name不为空
            System.out.println(1);
            List<CRM_Employerbaseinfo> byEmployername = crm_employerbaseinfoJPA.findByEmployername(name);
            if(byEmployername.size()==0){
                return CRM_Result.build(200, "没有此员工姓名，请检查姓名是否输入正确");
            }else {
                List<CRM_Employerbaseinfo> byDepartmentnameEqualsAndAndEmployername = crm_employerbaseinfoJPA.findByDepartmentnameEqualsAndAndeAndEmployerName(departmentname, name);
                return CRM_Result.ok(byDepartmentnameEqualsAndAndEmployername);
            }

        }
        if((name==null||name.equals(""))&&(id != null && id.length() != 0) ){  //name为空，工号不为空
            System.out.println(2);
            int i = Integer.parseInt(id);
            List<CRM_Employerbaseinfo> byEmployerid = crm_employerbaseinfoJPA.findByEmployerid(i);
            if(byEmployerid.size()==0){
                return CRM_Result.build(200, "没有此工号，请检查工号是否输入正确");
            }else {
                List<CRM_Employerbaseinfo> byDepartmentnameEqualsAndAndEmployerid = crm_employerbaseinfoJPA.findByDepartmentnameEqualsAndAndeAndEmployerId(departmentname, i);
                System.out.println(byDepartmentnameEqualsAndAndEmployerid.size());
                if(byDepartmentnameEqualsAndAndEmployerid.size()==0){
                    return CRM_Result.build(200, "该部门没有对应的员工");
                }
                return CRM_Result.ok(byDepartmentnameEqualsAndAndEmployerid);
            }

        }
        if((name != null && name.length() != 0)&&(id != null && id.length() != 0 )){  //name不为空，工号不为空
            System.out.println(3);
            int i = Integer.parseInt(id);
            List<CRM_Employerbaseinfo> byEmployerid = crm_employerbaseinfoJPA.findByEmployerid(i);
            if(byEmployerid.size()==0){
                return CRM_Result.build(200, "没有此工号，请检查工号是否输入正确");
            }else {
                List<CRM_Employerbaseinfo> byDepartmentnameEqualsAndAndEmployerid = crm_employerbaseinfoJPA.findByDepartmentnameEqualsAndAndeAndEmployerId(departmentname, i);
                return CRM_Result.ok(byDepartmentnameEqualsAndAndEmployerid);
            }
        }
        if((id==null||id.equals(""))&&(name == null && name.equals("")) ) {  //name为空，工号为空
            System.out.println(4);
            List<CRM_Employerbaseinfo> byDepartmentnameEquals = crm_employerbaseinfoJPA.findByDepartmentnameEquals(departmentname);
            return CRM_Result.ok(byDepartmentnameEquals);
        }
        System.out.println(5);

            return CRM_Result.ok(crm_employerbaseinfoJPA.findByDepartmentnameEquals(departmentname));
    }

    @Override
    public Page<CRM_Employerbaseinfo> selcetEmployer(String page, String size) {

        int parseInt = Integer.parseInt(page);
        int parseInt1 = Integer.parseInt(size);
        Pageable pageable = PageRequest.of(parseInt-1, parseInt1);
        return crm_employerbaseinfoJPA.findAll(pageable);
    }

    @Override
    public CRM_Result selcetEmployerbaseinfo() {

        List<CRM_Employerbaseinfo> all = crm_employerbaseinfoJPA.findAll();
        return CRM_Result.ok(all);

    }

    @Override
    public CRM_Result selectEmployerByphone(String phone) {
        List<CRM_Employerbaseinfo> byPhoneEquals = crm_employerbaseinfoJPA.findByPhoneEquals(phone);

        return CRM_Result.ok(byPhoneEquals.get(0).getEmployerid());
    }

    @Override
    public CRM_Result selectEmployerBysex(String sex) {

        List<CRM_Employerbaseinfo> bySexEquals = crm_employerbaseinfoJPA.findBySexEquals(sex);
        return CRM_Result.ok(bySexEquals);

    }


    @Override
    public CRM_Result addEmployerbaseinfo(String employer_name, String sex, String jointime, String phone, String card_id, String remark, String departmentname) {

        CRM_Employerbaseinfo crm_employerbaseinfo=new CRM_Employerbaseinfo();
        crm_employerbaseinfo.setEmployerName(employer_name);
        crm_employerbaseinfo.setSex(sex);
        crm_employerbaseinfo.setJointime(jointime);
        crm_employerbaseinfo.setPhone(phone);
        crm_employerbaseinfo.setCardId(card_id);
        crm_employerbaseinfo.setRemark(remark);
        crm_employerbaseinfo.setDepartmentname(departmentname);

        crm_employerbaseinfoJPA.save(crm_employerbaseinfo);
        return  CRM_Result.ok();
    }

    @Override
    public CRM_Result newaddEmployerbaseinfo(CRM_Employerbaseinfo crm_employerbaseinfo) {

        crm_employerbaseinfoJPA.save(crm_employerbaseinfo);
        List<CRM_Employerbaseinfo> byPhoneEquals = crm_employerbaseinfoJPA.findByPhoneEquals(crm_employerbaseinfo.getPhone());
        return CRM_Result.ok(byPhoneEquals.get(0).getEmployerId());
    }

    @Override
    public CRM_Result addEmployparticularinfo(String phone,String qualifications, String ismarry, String birthday, String parentphone, String natives, String address, String interesting, String certificate, String pic, String ill, String fixedphone, String learnship, String state, String pwd) {
        List<CRM_Employerbaseinfo> byPhoneEquals = crm_employerbaseinfoJPA.findByPhoneEquals(phone);
        int employer_id = byPhoneEquals.get(0).getEmployerId();
        System.out.println(employer_id);


        CRM_Employparticularinfo crm_employparticularinfo=new CRM_Employparticularinfo();

        crm_employparticularinfo.setEmployer_id(employer_id);
        crm_employparticularinfo.setQualifications(qualifications);
        crm_employparticularinfo.setIsmarry(ismarry);
        crm_employparticularinfo.setBirthday(birthday);
        crm_employparticularinfo.setParentphone(parentphone);
        crm_employparticularinfo.setAddress(address);
        crm_employparticularinfo.setCertificate(certificate);
        crm_employparticularinfo.setFixedphone(fixedphone);
        crm_employparticularinfo.setIll(ill);
        crm_employparticularinfo.setInteresting(interesting);
        crm_employparticularinfo.setLearnship(learnship);
        crm_employparticularinfo.setNatives(natives);
        crm_employparticularinfo.setPic(pic);
        crm_employparticularinfo.setPwd(pwd);
        crm_employparticularinfo.setState(state);



        crm_employparticularinfoJPA.save(crm_employparticularinfo);


        return CRM_Result.ok();
    }

    @Override
    public CRM_Result addEmployparticularinfo(CRM_Employparticularinfo crm_employparticularinfo) {



        return CRM_Result.ok();
    }

    @Override
    public CRM_Result selectEmployerinfo(String departmentname, String content, String type) {
        int a=0;
        int i = Integer.parseInt(type);
        if (1==i){//1是工号
            int i1 = Integer.parseInt(content);
            List<CRM_Employerbaseinfo> byEmployerid = crm_employerbaseinfoJPA.findByEmployerid(i1);
            System.out.println(123);
            System.out.println(byEmployerid.size());
            if(byEmployerid.size()==0){
                a=1;
                return CRM_Result.build(200, "没有此工号，请检查工号是否输入正确");
            }
            if(a==0) {
                List<CRM_Employerbaseinfo> byDepartmentnameEqualsaAndAndEmployer_id = crm_employerbaseinfoJPA.findByDepartmentnameEqualsAndAndeAndEmployerId(departmentname, i1);
                return CRM_Result.ok(byDepartmentnameEqualsaAndAndEmployer_id.get(0));
            }
        }if (2==i){//2是姓名
            List<CRM_Employerbaseinfo> byEmployername = crm_employerbaseinfoJPA.findByEmployername(content);
            if (byEmployername.size()==0){
                a=2;
                return CRM_Result.build(200, "没有此员工姓名，请检查姓名是否输入正确");
            }
            if(a==0) {
                List<CRM_Employerbaseinfo> byDepartmentnameEqualsAndAndEmployer_name = crm_employerbaseinfoJPA.findByDepartmentnameEqualsAndAndeAndEmployerName(departmentname, content);
                return CRM_Result.ok(byDepartmentnameEqualsAndAndEmployer_name.get(0));
            }
        }
        return CRM_Result.build(200, "什么都没有");

    }

    @Override
    public CRM_Result addallEmployerinfo(CRM_Employerbaseinfo crm_employerbaseinfo, CRM_Employparticularinfo crm_employparticularinfo) {

        CRM_Result crm_result = newaddEmployerbaseinfo(crm_employerbaseinfo);//保存基本信息
        Integer data = (Integer)crm_result.getData();
        int i = data.intValue();
        System.out.println(i);

        crm_employparticularinfo.setEmployer_id(i);
        crm_employparticularinfoJPA.save(crm_employparticularinfo);



        return CRM_Result.ok();
    }

    @Override
    public CRM_Result exportEmployerbaseinfo() {
        Employer_Wrapperclass employer_wrapperclass=new Employer_Wrapperclass();
        List<Employer_Wrapperclass> list=new ArrayList<>();
        List<CRM_Employerbaseinfo> all = crm_employerbaseinfoJPA.findAll();
        List<CRM_Employparticularinfo> all1 = crm_employparticularinfoJPA.findAll();
        System.out.println(all.size());
        for (int i=0;i<all.size();i++
             ) {
         employer_wrapperclass.setEmployer_id(all.get(i).getEmployerId());
          employer_wrapperclass.setEmployer_name(all.get(i).getEmployerName());
          employer_wrapperclass.setSex(all.get(i).getSex());
          employer_wrapperclass.setJointime(all.get(i).getJointime());
          employer_wrapperclass.setDepartmentname(all.get(i).getDepartmentname());
          employer_wrapperclass.setPhone(all.get(i).getPhone());
          employer_wrapperclass.setCard_id(all.get(i).getCardId());
          employer_wrapperclass.setRemark(all.get(i).getRemark());
          employer_wrapperclass.setUpdate_status(all.get(i).getUpdate_status());
            for (i=0;i<all1.size();i++){
                employer_wrapperclass.setState(all1.get(i).getState());
            }

        }

        System.out.println(list.size());

        int i=0;


//        for (Employer_Wrapperclass info:list
//             ) {
//
//            for (i=0;i<all1.size();)
//             {
//                 info.setBirthday(all1.get(i).getBirthday());
//                 info.setIll(all1.get(i).getIll());
//                 info.setQualifications(all1.get(i).getQualifications());
//                 info.setIsmarry(all1.get(i).getIsmarry());
//                 info.setParent_phone(all1.get(i).getParent_phone());
//                 info.setNatives(all1.get(i).getNatives());
//                 info.setAddress(all1.get(i).getAddress());
//                 info.setFixedphone(all1.get(i).getFixedphone());
//                 info.setState(all1.get(i).getState());
//                 info.setInteresting(all1.get(i).getInteresting());
//                 info.setPic(all1.get(i).getPic());
//                 info.setPwd(all1.get(i).getPwd());
//                 info.setLearnship(all1.get(i).getLearnship());
//                 info.setCertificate(all1.get(i).getCertificate());
//                   break;
//            }
//
//
//        }

        System.out.println(list.get(0));

        return CRM_Result.ok(list);*/









