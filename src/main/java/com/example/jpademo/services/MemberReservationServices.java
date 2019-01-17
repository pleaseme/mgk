package com.example.jpademo.services;

import com.example.jpademo.common.LBK_Result;
import com.example.jpademo.entity.MemberReservation;

import javax.servlet.http.HttpServletRequest;

public interface MemberReservationServices {

    //添加预定行程信息
    LBK_Result addreservainfo(HttpServletRequest request, MemberReservation memberReservation);
}
