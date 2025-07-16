package com.rabbiter.lm.controller;

import com.rabbiter.lm.model.entity.LoginLog;
import com.rabbiter.lm.model.entity.SystemLog;
import com.rabbiter.lm.model.vo.SystemLogVo;
import com.rabbiter.lm.service.LoginLogService;
import com.rabbiter.lm.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LogController {
    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    private SystemLogService systemLogService;

    @GetMapping("/loginlog")
    public List<LoginLog> getLoginLog(){
        List<LoginLog> all = loginLogService.getAll();
        return all;
    }

    @DeleteMapping("/loginlog")
    public void delLoginLog(String id){
        loginLogService.delLoginLog(id);
    }


    @GetMapping("/systemlog")
    public List<SystemLog> getSystemLog(){
        return systemLogService.getAll();
    }

    @DeleteMapping("/systemlog")
    public void deleteSystemLogById(String id){
        systemLogService.delete(id);
    }

    @GetMapping("/querySystemlog")
    public List<SystemLog> querySystemlog(SystemLogVo systemLogVo){
        System.out.println(systemLogVo);
        return systemLogService.query(systemLogVo);
    }
}
