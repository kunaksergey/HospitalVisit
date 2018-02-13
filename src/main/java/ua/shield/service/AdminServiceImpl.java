package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.shield.entity.Admin;
import ua.shield.repository.AdminRepository;


@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin add(Admin admin) {
        return adminRepository.save(admin);
    }
}
