package com.example.jpademo.controller;

import com.example.jpademo.common.LBK_Result;
import com.example.jpademo.entity.MemberReservation;
import com.example.jpademo.services.MemberReservationServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping(value = "/menberreserva")
public class MemberReservationController {

    @Autowired
    private MemberReservationServices memberReservationServices;


    /**提交预定行程*/
    @PostMapping(value = "/add")
    public LBK_Result add(HttpServletRequest request, MemberReservation memberReservation) {
        return memberReservationServices.addreservainfo(request, memberReservation);
    }



}
