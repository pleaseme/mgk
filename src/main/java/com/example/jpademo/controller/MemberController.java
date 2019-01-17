package com.example.jpademo.controller;


import com.example.jpademo.common.LBK_Result;
import com.example.jpademo.entity.LbkMember;
import com.example.jpademo.services.MemberServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping(value = "/member")
public class MemberController {

    @Autowired
    private MemberServices memberServices;


    /**根据token获取用户信息*/
    @PostMapping(value = "/getmemberinfobytoken")
    public LBK_Result getmemberinfobytoken(HttpServletRequest request) {
        return memberServices.selectowninfo(request);
    }


/**
 * 查詢所有用戶
 * */
    @RequestMapping(value = "/test")
    public LBK_Result testneew() {
        return memberServices.select();
    }


    @PostMapping(value = "/add")
    public LBK_Result testadd(LbkMember lbkMember) {
        return memberServices.add(lbkMember);
    }




}
