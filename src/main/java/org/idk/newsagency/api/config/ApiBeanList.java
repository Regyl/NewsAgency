package org.idk.newsagency.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiBeanList {

    @Bean
    public ModelMapper mapper() {
        ModelMapper mapper = new ModelMapper();
//        mapper.getConfiguration()
        return mapper;
    }
}
