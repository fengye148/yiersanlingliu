package com.fengye.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.fengye.common.context.LoginMemberContext;
import com.fengye.common.utils.SnowUtil;
import com.fengye.domain.Passenger;
import com.fengye.mapper.PassengerMapper;
import com.fengye.req.PassengerSaveReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class PassengerService {
    @Autowired
    private PassengerMapper passengerMapper;

    public void save(PassengerSaveReq req) {
        DateTime now = DateTime.now();
        Passenger passenger = BeanUtil.toBean(req, Passenger.class);
        passenger.setId(LoginMemberContext.getId());
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        passengerMapper.insert(passenger);
    }
}
