package com.soignemoi.soignemoiapi.security;

import com.soignemoi.soignemoiapi.data.UserEntity;
import com.soignemoi.soignemoiapi.data.values.Role;
import com.soignemoi.soignemoiapi.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        UserEntity user = userService.findByIdentifier(identifier);
        return new User(user.getUsername(), user.getPassword(), mapRoleToAuthorities(user.getRole()));
    }

    public Collection<GrantedAuthority> mapRoleToAuthorities(Role role) {
        return List.of(new SimpleGrantedAuthority(role.getRoleName()));
    }
}
