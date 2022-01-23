package org.idk.newsagency.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityBeanList {

    @Bean
    protected PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2Y);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(WebSecurityConfigurerAdapter adapter) throws Exception {
        return adapter.authenticationManagerBean();
    }

}
