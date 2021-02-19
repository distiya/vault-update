package com.topcoder.poc.vaultupdate.security;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.endpoint.RefreshEndpoint;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This adapter has the responsibility to configure http security with {@link SecurityRefreshFilter}
 */
@RequiredArgsConstructor
public class SecurityRefreshConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final UserPrincipal userPrincipal;
    private final RefreshEndpoint refreshEndpoint;

    @Override
    public void configure(HttpSecurity builder) {
        SecurityRefreshFilter filter = new SecurityRefreshFilter(userPrincipal,refreshEndpoint);
        builder.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
