package com.topcoder.poc.vaultupdate;

import com.topcoder.poc.vaultupdate.security.UserPrincipal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(UserPrincipal.class)
public class VaultUpdateApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaultUpdateApplication.class, args);
	}

}
