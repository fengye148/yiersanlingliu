package com.fengye.controller;

import com.fengye.common.Result.CommonResp;
import com.fengye.req.MemberLoginReq;
import com.fengye.req.MemberRegisterReq;
import com.fengye.req.MemberSendCodeReq;
import com.fengye.resp.MemberLoginResp;
import com.fengye.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @GetMapping("/count")
    public CommonResp<Integer> count() {
        CommonResp<Integer> commonResp = new CommonResp<>();
        commonResp.setContent(memberService.testCount());
        return commonResp;
    }
    @PostMapping("/register")
    public CommonResp<Long> register(@Valid @RequestBody MemberRegisterReq memberRegisterReq) {
        CommonResp<Long> commonResp = new CommonResp<>();
        Long id = memberService.register(memberRegisterReq.getMobile());
        commonResp.setContent(id);
        return commonResp;
    }

    @PostMapping("/send-code")
    public CommonResp<Long> sendCode(@Valid @RequestBody MemberSendCodeReq MemberSendCodeReq) {
        memberService.sendCode(MemberSendCodeReq);
        return new CommonResp<>();
    }

    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(@Valid @RequestBody MemberLoginReq req) {
        return new CommonResp<>(memberService.Login(req));
    }
}
