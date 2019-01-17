package com.example.jpademo.services;

import com.example.jpademo.common.LBK_Result;
import com.example.jpademo.entity.LbkMember;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MemberServices  {

    //根據token獲取用戶信息
    LBK_Result selectowninfo(HttpServletRequest request);

    //查询所有用户信息
     LBK_Result select();

     //添加用戶信息
    LBK_Result add(LbkMember lbkMember);

    //根據昵稱查詢
    List<LbkMember> findbynickname(String nickname);

    //根據用戶主鍵查詢用戶
    List<LbkMember> findbyid(Integer id);

}
