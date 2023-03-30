package com.electronics.store.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelmapperConfig {
    //Modelmapper is used to convert one object to another object
    @Bean
    public ModelMapper modelmapper() {
        return new ModelMapper();
    }
}
