package com.topcoder.poc.vaultupdate.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ConfigurationProperties
public class UserPrincipal {

    private String securityUserName;
    private String securityPassword;

}
