package com.topcoder.poc.vaultupdate.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.endpoint.RefreshEndpoint;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;

@Slf4j
@RequiredArgsConstructor
public class SecurityRefreshFilter extends GenericFilterBean {

    private final UserPrincipal userPrincipal;
    private final RefreshEndpoint refreshEndpoint;

    private static final String BASIC_TOKEN_PREFIX = "basic ";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        checkUserNameAndPasswordValidForBasicAuthAndRefreshIfNecessary((HttpServletRequest) servletRequest);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void checkUserNameAndPasswordValidForBasicAuthAndRefreshIfNecessary(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.toLowerCase().startsWith(BASIC_TOKEN_PREFIX)) {
            String[] creds = new String(Base64.getDecoder().decode(authHeader.substring(6))).split(":");
            if(creds.length == 2 && (!userPrincipal.getSecurityUserName().equals(creds[0]) || !userPrincipal.getSecurityPassword().equals(creds[1]))){
                refreshEndpoint.refresh();
            }
        }
    }
}
