package pl.lodz.uni.edu.gin.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.lodz.uni.edu.gin.entities.AppUser;
import pl.lodz.uni.edu.gin.security.filters.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    private static final String ADMIN = AppUser.Role.ADMIN.name();

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()

                .requestMatchers("/error")
                .permitAll()

                .requestMatchers(HttpMethod.OPTIONS)
                .permitAll()

                .requestMatchers("/carts/**")
                .permitAll()

                .requestMatchers(HttpMethod.GET)
                .permitAll()

                .requestMatchers(HttpMethod.POST, "/register", "/authenticate")
                .anonymous()

                .anyRequest()
                .hasAuthority(ADMIN)

                .and()
                .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
