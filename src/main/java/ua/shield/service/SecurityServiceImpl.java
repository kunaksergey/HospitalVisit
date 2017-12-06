package ua.shield.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service("securityService")
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public String findLoggedInUsername() {
        return null;
    }

    @Override
    public void autoLogin(String username, String password) {

    }
}
