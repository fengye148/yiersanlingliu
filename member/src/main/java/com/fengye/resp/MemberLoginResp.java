package com.fengye.resp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.aspectj.apache.bcel.classfile.Code;

public class MemberLoginResp {

    private String mobile;

    private Long id;

    private String token;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MemberLoginResp{");
        sb.append("mobile='").append(mobile).append('\'');
        sb.append(", id=").append(id);
        sb.append(", token='").append(token).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

