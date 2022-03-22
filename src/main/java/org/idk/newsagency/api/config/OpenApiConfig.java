package org.idk.newsagency.api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    /*static {
        SpringDocUtils.getConfig().replaceParameterObjectWithClass(org.springframework.data.domain.Pageable.class, org.springdoc.core.converters.models.Pageable.class)
                .replaceParameterObjectWithClass(org.springframework.data.domain.PageRequest.class, org.springdoc.core.converters.models.Pageable.class);
    }*/

    static {
        SpringDocUtils.getConfig().replaceWithClass(org.springframework.data.domain.Pageable.class,
                org.springdoc.core.converters.models.Pageable.class);
    }

    private static final String BEARER = "bearer";
    private static final String BEARER_KEY = "bearer-key";
    private static final String BEARER_FORMAT = "JWT";

    @Bean
    public OpenAPI customOpenAPI() {
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme(BEARER)
                .bearerFormat(BEARER_FORMAT);
        Components components = new Components().addSecuritySchemes(BEARER_KEY, securityScheme);
        OpenAPI openApi = new OpenAPI().components(components);
        SecurityRequirement securityRequirement = new SecurityRequirement();
        securityRequirement.addList(BEARER_KEY);
        openApi.addSecurityItem(securityRequirement);
        return openApi;
    }
}
