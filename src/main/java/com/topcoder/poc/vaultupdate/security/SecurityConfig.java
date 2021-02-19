package com.topcoder.poc.vaultupdate.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.endpoint.RefreshEndpoint;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserPrincipal userPrincipal;

    @Autowired(required = false)
    private RefreshEndpoint refreshEndpoint;

    /**
     * All the requests to the server is protected with http basic authentication
     *
     * @param http The configured HttpSecurity instance
     * @throws Exception when an error
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Registering the filter to intercept the request
        http.apply(new SecurityRefreshConfigurer(userPrincipal,refreshEndpoint));
    }
}
