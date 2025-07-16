package com.rabbiter.lm.service;

import com.rabbiter.lm.model.dto.LoginDto;
import com.rabbiter.lm.model.entity.Admin;
import com.rabbiter.lm.model.entity.LoginLog;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface LoginLogService {
    List<LoginLog> getAll();
    void recordLog(LoginDto loginDto, Admin admin, HttpServletRequest request);
    void delLoginLog(String id);
}
