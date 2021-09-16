package com.sushmita.github.spring_security_oauth.springsecurityoauth.config;

import com.sushmita.github.spring_security_oauth.springsecurityoauth.converter.SecurityRoleConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;


@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
public class SpringWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private String PROFILE_SCOPE = "SCOPE_profile";
    private String ROLE = "openid-role";

    // it will extract the jwt token and check its
    protected void configure(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        SecurityRoleConverter roleConverter = new SecurityRoleConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(roleConverter);

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/token").hasAuthority(PROFILE_SCOPE)
                .antMatchers(HttpMethod.GET,"/status").hasRole(ROLE)
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter);


    }
}
