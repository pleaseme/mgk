package com.example.jpademo.services.impl;

import com.example.jpademo.common.DateUtil;
import com.example.jpademo.common.LBK_Result;
import com.example.jpademo.entity.LbkMember;

import com.example.jpademo.jpa.MemberRepository;
import com.example.jpademo.services.MemberServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class MemberServicesImpl implements MemberServices {

    /**redis緩存*/
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public LBK_Result selectowninfo(HttpServletRequest request) {

        //redis
        RedisSerializer redisSerializer = new StringRedisSerializer();//序列化
        redisTemplate.setKeySerializer(redisSerializer);

        String token = request.getHeader("token");//獲取请求头的token
        System.out.println(token);
        if (token == null) {
            return null;
        } else {

            List<LbkMember> lbkMembers = (List<LbkMember>) redisTemplate.opsForValue().get(token);
            Integer id = lbkMembers.get(0).getId();//获取token里面的id
            List<LbkMember> byIdEquals = memberRepository.findByIdEquals(id);
            return LBK_Result.ok(byIdEquals.get(0));
        }
    }



    @Override
    public LBK_Result select() {
        List<LbkMember> all = memberRepository.findAll();
        return LBK_Result.ok(all);
    }

    @Override
    public LBK_Result add(LbkMember lbkMember) {
        lbkMember.setCreateTime( DateUtil.getFormatDate(new Date()));
         lbkMember.setIntegral(0);
         lbkMember.setMemberStatus(0);
        memberRepository.save(lbkMember);
        return LBK_Result.ok();
    }

    @Override
    public List<LbkMember> findbynickname(String nickname) {
        List<LbkMember> byNicknameEquals = memberRepository.findByNicknameEquals(nickname);
        return byNicknameEquals;
    }

    @Override
    public List<LbkMember> findbyid(Integer id) {
        List<LbkMember> byIdEquals = memberRepository.findByIdEquals(id);
        return byIdEquals;
    }


}
