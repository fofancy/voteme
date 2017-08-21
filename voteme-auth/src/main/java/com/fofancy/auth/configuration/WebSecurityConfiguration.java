package com.fofancy.auth.configuration;

import com.fofancy.auth.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final static String LOGIN_PAGE_URL = "/account/auth/login";

    @Autowired
    private UnauthorizedEntryPoint unauthorizedEntryPoint;
    @Autowired
    private AuthenticationSuccessHandler successHandler;
    @Autowired
    private AuthenticationFailureHandler failureHandler;
    @Autowired
    private UsernamePasswordProvider usernamePasswordProvider;
    @Autowired
    private TokenAuthenticationProvider tokenProvider;
    @Autowired
    private AuthenticationManager manager;

    private UsernamePasswordLoginFilter buildUsernameLoginFilter() {
        UsernamePasswordLoginFilter filter = new UsernamePasswordLoginFilter(
                LOGIN_PAGE_URL,
                successHandler,
                failureHandler
        );

        filter.setAuthenticationManager(manager);
        return filter;
    }

    private TokenAuthenticationFilter buildTokenAuthenticationFilter() {
        TokenAuthenticationFilter filter = new TokenAuthenticationFilter(new TokenAuthenticationRequestMatcher());

        filter.setAuthenticationManager(manager);
        return filter;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecureRandom secureRandom() {
        return new SecureRandom();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(usernamePasswordProvider)
                .authenticationProvider(tokenProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/account/personal-page/content")
                        .access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                    .antMatchers("/account/admin/content")
                        .access("hasRole('ROLE_ADMIN')")
                    .anyRequest().permitAll()
                    .and()
                .formLogin()
                    .loginPage("/account/auth/")
                    .permitAll()
                    .failureUrl("/auth-failed")
                    .and()
                .addFilterBefore(
                        buildUsernameLoginFilter(),
                        UsernamePasswordAuthenticationFilter.class
                )
                .addFilterBefore(
                        buildTokenAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class
                );
    }
}
