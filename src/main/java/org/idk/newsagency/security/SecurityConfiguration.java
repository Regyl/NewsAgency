package org.idk.newsagency.security;

import org.idk.newsagency.entity.enumeration.Role;
import org.idk.newsagency.security.filter.JwtFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtFilter jwtFilter;

    public SecurityConfiguration(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/sign-in"
                ).permitAll()
                .antMatchers(HttpMethod.DELETE, "/announcements/**").hasAnyAuthority(Role.ADMIN.name(), Role.USER.name())
                .antMatchers(HttpMethod.POST, "/announcements/**").hasAnyAuthority(Role.ADMIN.name(), Role.USER.name())
                .antMatchers(HttpMethod.PUT, "/announcements/**").hasAnyAuthority(Role.ADMIN.name(), Role.USER.name())
                .antMatchers("/announcements/moderation").hasAnyAuthority(Role.ADMIN.name(), Role.MODERATOR.name())
                .antMatchers(HttpMethod.GET, "/info").hasAnyAuthority(Role.ADMIN.name(), Role.USER.name(), Role.UNVERIFIED_USER.name())
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) {
        // Allow swagger to be accessed without authentication
        web.ignoring().antMatchers(
                "/v3/api-docs/**",
                "/swagger-ui",
                "/swagger-ui/**");
    }

}
