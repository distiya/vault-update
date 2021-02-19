package com.topcoder.poc.vaultupdate.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.cloud.endpoint.RefreshEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    @ConditionalOnMissingBean(RefreshEndpoint.class)
    public RefreshEndpoint getRefreshEndpoint(ContextRefresher contextRefresher){
        return new RefreshEndpoint(contextRefresher);
    }
}
