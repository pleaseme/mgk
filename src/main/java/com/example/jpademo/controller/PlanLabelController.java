package com.example.jpademo.controller;

import com.example.jpademo.common.LBK_Result;
import com.example.jpademo.services.PlanLabelServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/guideplanlabel")
public class PlanLabelController {

    @Autowired
    private PlanLabelServices planLabelServices;


    /**
     * 获取所有方案玩法路线标签
     * */
    @RequestMapping(value = "/get")
    public LBK_Result get() {
        return planLabelServices.selecroadtype();
    }
}
