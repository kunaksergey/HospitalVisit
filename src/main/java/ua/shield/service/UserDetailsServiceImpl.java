package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.shield.entity.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        Set<GrantedAuthority> grantedAuthoritySet=new HashSet<>();

        grantedAuthoritySet.addAll(user.getRoles()
                .stream()
                .map(r->new SimpleGrantedAuthority(r.getRole().name()))
                .collect(Collectors.toSet())
        );
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),grantedAuthoritySet);
    }
}
