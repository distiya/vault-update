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

/**
 * All the requests to the server will be intercepted by this filter. The authorization header
 * will be inspected by this filter and check whether it contains basic authentication header or not.
 * If the basic authentication header is present, the user name and password will be extracted and the
 * extracted values will be checked with the configured values in {@link UserPrincipal}. If those values
 * are not matching, {@link RefreshEndpoint#refresh()} will be invoked to refresh the configuration from
 * spring cloud config server which is backed by vault backend
 */
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
