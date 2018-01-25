package com.spring.start.service;

import com.spring.start.entity.Role;
import org.springframework.security.core.userdetails.User;
import com.spring.start.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.spring.start.entity.User user = userRepository.findByUsername(username);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
        return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(com.spring.start.entity.User user, List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true,
                true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(List<Role> userRoles) {
        List<GrantedAuthority> setAuths = userRoles.stream()
                                                    .map(f -> new SimpleGrantedAuthority(f.getName()))
                                                    .collect(Collectors.toList());

        return setAuths;
    }


}
