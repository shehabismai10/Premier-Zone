package com.pl.premier_zone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pl.premier_zone.user.UserRepository;

@Configuration
// شيلنا الـ RequiredArgsConstructor وكتبنا الـ Constructor يدوي تحت
public class ApplicationConfig {

    private final UserRepository repository;

    // الـ Constructor اليدوي عشان نضمن إن الـ Repository يحصل له Injection صح
    public ApplicationConfig(UserRepository repository) {
        this.repository = repository;
    }

    // this method is for loading the user details from the database using the email as the username
    @Bean
    public UserDetailsService userDetailsService() {
        return email -> repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        // بنستخدم DaoAuthenticationProvider عشان نربط الـ UserDetailsService بالـ PasswordEncoder
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}