package dev.antoniogrillo.esempiocinecaavanzato.configuration;

import com.sun.net.httpserver.HttpsConfigurator;
import dev.antoniogrillo.esempiocinecaavanzato.filter.JWTFilter;
import dev.antoniogrillo.esempiocinecaavanzato.model.Ruolo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;


@Component
@EnableWebSecurity
@RequiredArgsConstructor
public class GestorePath {

    private final JWTFilter jwtFilter;
    private final AuthenticationProvider provider;

    @Bean
    protected SecurityFilterChain getChain(HttpSecurity http){
        http.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(h->h.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeHttpRequests(a->a
                        .requestMatchers(HttpMethod.POST,"/all/login").permitAll()
                        .requestMatchers("/graphql").permitAll()
                        .requestMatchers("/all/**").permitAll()
                        .requestMatchers("/authorized/**").authenticated()
                        .requestMatchers("/utente/**").hasRole(Ruolo.UTENTE.name())
                        .requestMatchers("/admin/**").hasAnyRole(Ruolo.ADMIN.name(), Ruolo.SUPERADMIN.name())
                        .anyRequest().permitAll()
                ).authenticationProvider(provider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
