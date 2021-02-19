package com.topcoder.poc.vaultupdate.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VaultUserDetailsService implements UserDetailsService {

    @Autowired
    private UserPrincipal userPrincipal;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("Using user {} and password {} for authentication",this.userPrincipal.getSecurityUserName(),this.userPrincipal.getSecurityPassword());
        if(!userName.equals(this.userPrincipal.getSecurityUserName())){
            throw new UsernameNotFoundException("User not found");
        }
        return new VaultUser(this.userPrincipal.getSecurityUserName(),"{noop}"+this.userPrincipal.getSecurityPassword());
    }
}
