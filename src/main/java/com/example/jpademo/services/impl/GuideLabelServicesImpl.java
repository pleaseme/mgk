package com.example.jpademo.services.impl;

import com.example.jpademo.common.LBK_Result;
import com.example.jpademo.entity.GuideLabel;
import com.example.jpademo.jpa.GuideLabelRepository;
import com.example.jpademo.services.GuideLabelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class GuideLabelServicesImpl implements GuideLabelServices {


    @Autowired
    private GuideLabelRepository guideLabelRepository;


    @Override
    public LBK_Result getLabel() {
        List<GuideLabel> list = new ArrayList<>();
        List<GuideLabel> all = guideLabelRepository.findAll();
        for (GuideLabel label : all
        ) {
            label.setId(null);
            label.setRank(null);
            list.add(label);
        }
        return LBK_Result.ok(list);
    }




}


