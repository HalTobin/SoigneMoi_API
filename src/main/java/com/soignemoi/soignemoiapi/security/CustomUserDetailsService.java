package com.soignemoi.soignemoiapi.security;

import com.soignemoi.soignemoiapi.data.model.Visitor;
import com.soignemoi.soignemoiapi.data.values.Role;
import com.soignemoi.soignemoiapi.repository.VisitorRepository;
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
    private VisitorRepository visitorRepository;

    // Mail is used as username
    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Visitor visitor = visitorRepository
                .findByMail(mail)
                .orElseThrow(() -> new UsernameNotFoundException("Mail not found"));
        return new User(visitor.getMail(), visitor.getPassword(), mapRoleToAuthorities(visitor.getRole()));
    }

    private Collection<GrantedAuthority> mapRoleToAuthorities(Role role) {
        return List.of(new SimpleGrantedAuthority(role.getRoleName()));
    }
}
