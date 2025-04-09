package com.fengye.controller;

import com.fengye.common.Result.CommonResp;
import com.fengye.req.MemberLoginReq;
import com.fengye.req.MemberRegisterReq;
import com.fengye.req.MemberSendCodeReq;
import com.fengye.req.PassengerSaveReq;
import com.fengye.resp.MemberLoginResp;
import com.fengye.service.MemberService;
import com.fengye.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passenger")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;

    @PostMapping("/save")
    public CommonResp<Long> save(@Valid @RequestBody PassengerSaveReq req) {
        passengerService.save(req);
        return new CommonResp<>(1L);
    }
    
}
