package com.example.CafeTour.config;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringConfig  {

    private final DataSource dataSource;

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable().cors().disable()
                .authorizeRequests()
                    .antMatchers("/home","/","/JoinForm","/join","/homepage","/signup","/signup_form").permitAll()
                    .anyRequest().authenticated()
                    .and()

                .formLogin()
                    .loginPage("/LoginForm").permitAll()
                    .loginProcessingUrl("/LoginForm") //LoginForm을 만나면 시큐리티가 낚아챔
                    .defaultSuccessUrl("/kakao")// 패스워드 파라미터명 설정
                      .and()

                .logout()
                    .permitAll();
        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
/*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(encoderPwd())
                .usersByUsernameQuery("select email,Pw, enabled "
                        + "from user "
                        + "where email = ?")
                .authoritiesByUsernameQuery("select email,authority "
                        + "from user "
                        + "where email = ?");
    }*/
}

