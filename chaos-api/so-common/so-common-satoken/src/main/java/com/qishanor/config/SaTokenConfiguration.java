package com.qishanor.config;

import com.qishanor.service.StpInterfaceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaTokenConfiguration {

    @Bean
    public SaTokenConfigure saTokenConfigure(){
        return new SaTokenConfigure();
    }
    
    @Bean
    public StpInterfaceImpl stpInterface(){
        return new StpInterfaceImpl();
    }
}



