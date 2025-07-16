package com.rabbiter.lm.service;

import com.rabbiter.lm.model.entity.SystemLog;
import com.rabbiter.lm.model.vo.SystemLogVo;

import java.util.List;

public interface SystemLogService {
    public void record(SystemLog log);
    public List<SystemLog> getAll();
    public void delete(String id);
    public List<SystemLog> query(SystemLogVo systemLogVo);
}
