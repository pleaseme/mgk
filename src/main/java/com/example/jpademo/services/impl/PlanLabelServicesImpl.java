package com.example.jpademo.services.impl;

import com.example.jpademo.common.LBK_Result;
import com.example.jpademo.entity.PlanLabel;
import com.example.jpademo.jpa.PlanLabelRepository;
import com.example.jpademo.services.PlanLabelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanLabelServicesImpl implements PlanLabelServices {

    @Autowired
    private PlanLabelRepository planLabelRepository;


    @Override
    public LBK_Result selecroadtype() {
        List<PlanLabel> all = planLabelRepository.findAll();
        return LBK_Result.ok(all);
    }
}
