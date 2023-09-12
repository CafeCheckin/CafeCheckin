package com.example.CafeTour.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringConfig{

    private final AuthenticationFailureHandler customFailureHandler;

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic().and()
                .csrf().disable().cors().disable()
                .authorizeRequests()
                .antMatchers("/lll").authenticated()
                .anyRequest().permitAll()
                .and()

                .formLogin()
                    .loginPage("/lll")
                    .permitAll()
                    .loginProcessingUrl("/lll") //LoginForm을 만나면 시큐리티가 낚아챔
                    .defaultSuccessUrl("/cafe",true)// 패스워드 파라미터명 설정
                    .and()

                .logout()
                        .permitAll()
                     .logoutSuccessUrl("/home")
                    .invalidateHttpSession(true);
        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}

