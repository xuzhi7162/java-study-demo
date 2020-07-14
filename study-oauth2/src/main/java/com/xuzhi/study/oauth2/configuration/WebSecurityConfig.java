package com.xuzhi.study.oauth2.configuration;

import com.xuzhi.study.oauth2.security.client.GiteeOAuth2AccessTokenResponseClient;
import com.xuzhi.study.oauth2.security.model.GiteeOAuth2User;
import com.xuzhi.study.oauth2.security.service.GiteeOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userService;

    @Autowired
    private GiteeOAuth2AccessTokenResponseClient accessTokenResponseClient;

    @Autowired
    private GiteeOAuth2UserService oAuth2UserService;

    @Bean
    public PasswordEncoder initPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login/gitee").loginProcessingUrl("/login/form")
                .and()
                .authorizeRequests()
                .antMatchers("/hello").hasAnyAuthority("ROLE_USER")
                .antMatchers("/**").permitAll()
                .and()
                .logout().deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/").permitAll();

        http.oauth2Login().loginPage("/login/gitee")
                .tokenEndpoint().accessTokenResponseClient(accessTokenResponseClient)
                .and()
                .userInfoEndpoint().customUserType(GiteeOAuth2User.class,"gitee")
                .userService(oAuth2UserService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(initPasswordEncoder());
    }
}
