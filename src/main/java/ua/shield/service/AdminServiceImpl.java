package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shield.entity.Admin;
import ua.shield.repository.AdminRepository;

/**
 * Created by sa on 22.12.17.
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Override
    public Admin add(Admin admin) {
        return adminRepository.save(admin);
    }
}
