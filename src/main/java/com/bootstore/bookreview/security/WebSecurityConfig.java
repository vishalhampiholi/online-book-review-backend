package com.bootstore.bookreview.security;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("ADMIN", "USER")
                .antMatchers("/").hasAnyRole("ADMIN", "USER")
                .antMatchers("/").hasAnyRole("ADMIN","USER")
                .antMatchers("/**").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/forgot").permitAll()
                .antMatchers("/reset").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .anyRequest().permitAll().and().formLogin().loginPage("/index.html").loginProcessingUrl("/login")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json");
                        Gson gson = new Gson();
                        String role = gson.toJson(authentication.getAuthorities());
                        response.getWriter().write(role);
                        response.setStatus(200);
                    }
                }).failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                                        AuthenticationException exception) throws IOException, ServletException {
                        response.setStatus(401);
                    }

                }).and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                .logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)))
                .deleteCookies("JESSIONID").invalidateHttpSession(true);

    }}
