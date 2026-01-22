package dev.antoniogrillo.esempiocinecaavanzato.configuration;

import dev.antoniogrillo.esempiocinecaavanzato.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomHealthConfigurator implements HealthIndicator {

    private final UtenteRepository repo;

    @Override
    public @Nullable Health health() {
        try{
            long orario=System.currentTimeMillis();
            long numeroUtenti=repo.count();
            long tempoDiRisposta=System.currentTimeMillis()-orario;
            if(numeroUtenti==0)
                return Health.down()
                    .withDetail("connessione", "nessun utente trovato")
                    .build();
            else
                return Health.up()
                        .withDetail("numeroUtenti", numeroUtenti)
                        .withDetail("tempoDiRisposta", tempoDiRisposta+" ms")
                        .build();
        }catch (Exception e){
            return Health.down().withException(e).build();
        }
    }
}
