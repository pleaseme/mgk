package com.example.jpademo.services.impl;


import com.example.jpademo.common.LBK_Result;
import com.example.jpademo.entity.LbkMember;
import com.example.jpademo.entity.MemberReservation;
import com.example.jpademo.jpa.MemberReservationRepository;
import com.example.jpademo.services.MemberReservationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class MemberReservationServicesImpl implements MemberReservationServices {

    @Autowired
    private MemberReservationRepository memberReservationRepository;

    /**redis緩存*/
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public LBK_Result addreservainfo(HttpServletRequest request, MemberReservation memberReservation) {
        Integer id= getIdBytoken(request);
        System.out.println(id);
        memberReservation.setMemberId(id);
        memberReservationRepository.save(memberReservation);
        return LBK_Result.build(200,"预定行程添加成功");
    }

    /**通用方法*/
    public Integer getIdBytoken(HttpServletRequest request){
        RedisSerializer redisSerializer = new StringRedisSerializer();//序列化
        redisTemplate.setKeySerializer(redisSerializer);
        List<LbkMember> lbkMembers = (List<LbkMember>) redisTemplate.opsForValue().get(request.getHeader("token"));
        return lbkMembers.get(0).getId();
    }


}
