package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Admin;
import com.kts.cultural_content.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService implements ServiceInterface<Admin>{
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Page<Admin> findAll(Pageable pageable) {
        return adminRepository.findAll(pageable);
    }

    @Override
    public Admin findOne(Integer id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public Admin create(Admin admin) throws Exception {
        if(adminRepository.findByKorisnickoIme(admin.getKorisnickoIme()) != null) {
            throw new Exception("Admin with given username already exists");
        }
        return adminRepository.save(admin);
    }

    @Override
    public Admin update(Admin entity, Integer id) throws Exception {
        Admin existingAdmin = adminRepository.findById(id).orElse(null);
        if(existingAdmin == null){
            throw new Exception("Admin with given id doesn't exist");
        }
        existingAdmin.setKorisnickoIme(entity.getKorisnickoIme());
        existingAdmin.setIme(entity.getIme());
        existingAdmin.setPrezime(entity.getPrezime());
        existingAdmin.setEmail(entity.getEmail());
        if(adminRepository.findByKorisnickoImeAndIdNot(existingAdmin.getKorisnickoIme(), id) != null) {
            throw new Exception("Admin with given username already exists");
        }
        return adminRepository.save(existingAdmin);
    }

    @Override
    public void delete(Integer id) throws Exception {
        Admin existingAdmin = adminRepository.findById(id).orElse(null);
        if(existingAdmin == null){
            throw new Exception("Admin with given id doesn't exist");
        }
        adminRepository.delete(existingAdmin);
    }
}
