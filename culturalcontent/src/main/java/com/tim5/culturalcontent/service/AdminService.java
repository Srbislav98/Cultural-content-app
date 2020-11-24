/*package com.tim5.culturalcontent.service;

import com.tim5.culturalcontent.model.Admin;
import com.tim5.culturalcontent.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AdminService implements ServiceInterface<Admin>{
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Override
    public Admin findOne(Integer id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public Admin create(Admin admin) throws Exception {
        return adminRepository.save(admin);
    }

    @Override
    public Admin update(Admin entity, Integer id) throws Exception {
        return null;
    }

    @Override
    public void delete(Integer id) throws Exception {
        Admin admin = adminRepository.findById(id).orElse(null);
        adminRepository.delete(admin);
    }
}*/
