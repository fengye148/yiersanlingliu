package com.fengye.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.jwt.JWTUtil;
import com.fengye.common.exception.BusinessException;
import com.fengye.common.exception.BusinessExceptionEnum;
import com.fengye.common.utils.JwtUtil;
import com.fengye.common.utils.SnowUtil;
import com.fengye.domain.Member;
import com.fengye.domain.MemberExample;
import com.fengye.mapper.MemberMapper;
import com.fengye.req.MemberLoginReq;
import com.fengye.req.MemberSendCodeReq;
import com.fengye.resp.MemberLoginResp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MemberService {
    @Autowired
    private MemberMapper memberMapper;

    public int testCount() {
        return (int)memberMapper.countByExample(null);
    }

    public Long register(String Mobile) {
        MemberExample example = new MemberExample();
        example.createCriteria().andMobileEqualTo(Mobile);
        List<Member> members = memberMapper.selectByExample(example);
        if(CollUtil.isNotEmpty(members)) {
            BusinessExceptionEnum e = BusinessExceptionEnum.MEMBER_MOBILE_EXIST;
            System.out.println(e);
            throw new BusinessException(e);
//            return members.get(0).getId();
        }
        Member member = new Member();
        member.setMobile(Mobile);
        member.setId(SnowUtil.getSnowflakeNextId());
        memberMapper.insert(member);
        return member.getId();
    }

    public void sendCode(MemberSendCodeReq req) {
        Member memberDB = SelectByMobile(req.getMobile());
        if(ObjectUtil.isNull(memberDB)) {
            Member member = new Member();
            member.setMobile(req.getMobile());
            member.setId(SnowUtil.getSnowflakeNextId());
            memberMapper.insert(member);
//            return members.get(0).getId();
        }

        //随机生成短信验证码

        //将短信验证码放入数据库中
    }

    public MemberLoginResp Login(MemberLoginReq req) {
        Member memberDB = SelectByMobile(req.getMobile());
        if(ObjectUtil.isNull(memberDB)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOT_EXIST);
//            return members.get(0).getId();
        }

        if(!"8888".equals(req.getCode())) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_ERROR);
        }
        MemberLoginResp resp = BeanUtil.copyProperties(memberDB,MemberLoginResp.class);
        String token = JwtUtil.createToken(resp.getId(),resp.getMobile());
        resp.setToken(token);
        return resp;
    }


    private Member SelectByMobile(String Mobile) {
        MemberExample example = new MemberExample();
        example.createCriteria().andMobileEqualTo(Mobile);
        List<Member> members = memberMapper.selectByExample(example);
        if(CollUtil.isNotEmpty(members)) {
            return members.get(0);
        }else {
            return null;
        }
    }
}
