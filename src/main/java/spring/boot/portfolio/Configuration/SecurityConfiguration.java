package spring.boot.portfolio.Configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
            authorizationManagerRequestMatcherRegistry
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/community/**").hasRole(Role.ADMIN.name())
                    .anyRequest().authenticated();
        });
        http.formLogin((httpSecurityFormLoginConfigurer) -> {
            httpSecurityFormLoginConfigurer
                    .loginPage("/")
                    .loginProcessingUrl("/certify")
                    .usernameParameter("Id")
                    .passwordParameter("Pw")
                    .successHandler(new AuthSuccessHandler());
        });
        http.logout(httpSecurityLogoutConfigurer -> {
            httpSecurityLogoutConfigurer
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true);
        });
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
