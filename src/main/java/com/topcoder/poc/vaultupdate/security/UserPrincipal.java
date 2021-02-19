package com.topcoder.poc.vaultupdate.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.endpoint.RefreshEndpoint;

/**
 * This configuration class is holding the value for username and password for http basic authentication
 * which will be refreshed automatically when invoke {@link RefreshEndpoint#refresh()}.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ConfigurationProperties
public class UserPrincipal {

    private String securityUserName = "vaultUser";
    private String securityPassword = "vaultPassword";

}
