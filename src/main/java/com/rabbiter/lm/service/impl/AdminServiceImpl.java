package com.rabbiter.lm.service.impl;

import com.rabbiter.lm.model.dto.LoginDto;
import com.rabbiter.lm.model.entity.Admin;
import com.rabbiter.lm.repository.AdminRepository;
import com.rabbiter.lm.service.AdminService;
import com.rabbiter.lm.utils.DataTimeUtil;
import com.rabbiter.lm.utils.JwtTokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminRepository adminRepository;

    @Override
    public Admin save(Admin admin) throws Exception {
        if (admin.getEmail().length() < 4 || admin.getPassword().length() < 5) throw new Exception("请求参数异常");
        Admin adminByEmail = adminRepository.findAdminByEmail(admin.getEmail());
        if(admin.getId() == null && null != adminByEmail) {
            throw new Exception("邮箱已存在");
        }
        admin.setCreateAt(DataTimeUtil.getNowTimeString());

        return adminRepository.save(admin);
    }

    @Override
    public Admin findById(String id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public Admin loginByPassword(LoginDto dto) throws Exception {
        Admin one = adminRepository.findAdminByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (one == null) {
            throw new Exception("邮箱或密码错误");
        }
        return one;
    }

    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Override
    public String createToken(Admin admin, long exp) {
        String rolesString = admin.getRoles();
        String[] roles = rolesString != null ? rolesString.split(";") : null;
        return JwtTokenUtil.createToken(admin.getEmail(), roles, exp);
    }

    @Override
    public void delete(String id) {
        adminRepository.deleteById(id);
    }

}
