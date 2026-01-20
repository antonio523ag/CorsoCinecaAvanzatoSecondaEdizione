package dev.antoniogrillo.esempiocinecaavanzato.configuration;

import dev.antoniogrillo.esempiocinecaavanzato.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class SecurityBeanConfig {

    private final UtenteRepository repo;

    @Bean
    protected UserDetailsService getUtente(){
        return username-> Objects.requireNonNull(repo.findByEmail(username)
                .orElse(null));
    }

    @Bean
    protected PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected AuthenticationManager getAuthenticationConfiguration(AuthenticationConfiguration authenticationConfiguration){
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected AuthenticationProvider get(){
        DaoAuthenticationProvider dap=new DaoAuthenticationProvider(getUtente());
        dap.setPasswordEncoder(getPasswordEncoder());
        return dap;
    }


}
