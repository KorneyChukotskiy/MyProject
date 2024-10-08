package com.anton.myproject.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login").permitAll() // Позволяет доступ к странице логина
                        .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login") // Указывает страницу логина
                        .defaultSuccessUrl("/home") // Перенаправление после успешной аутентификации
                        .failureUrl("/login?error=true") // Перенаправление на страницу логина с ошибкой
                        .permitAll()
                )
//                .logout(logout -> logout
//                        .permitAll()
//                )
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var user1 = User.withUsername("user1")
                .password(passwordEncoder().encode("password")) // Пароль: password
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
}
}