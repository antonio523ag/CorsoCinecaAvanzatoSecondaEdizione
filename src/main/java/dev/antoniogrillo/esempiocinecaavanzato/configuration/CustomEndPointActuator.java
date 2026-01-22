package dev.antoniogrillo.esempiocinecaavanzato.configuration;

import dev.antoniogrillo.esempiocinecaavanzato.repository.AutomobileRepository;
import dev.antoniogrillo.esempiocinecaavanzato.repository.CasaAutomobilisticaRepository;
import dev.antoniogrillo.esempiocinecaavanzato.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id = "mioEndPoint")
@RequiredArgsConstructor
public class CustomEndPointActuator {

    private final UtenteRepository repo;
    private final AutomobileRepository automobileRepo;
    private final CasaAutomobilisticaRepository casaAutomobilisticaRepo;

    @ReadOperation
    public Map<String,Object> getInfo(){
        Map<String,Object> m= new HashMap<>();
        long tempo=System.currentTimeMillis();
        long conteggio=repo.count();
        long tempoConnessione=System.currentTimeMillis()-tempo;
        m.put("numeroUtenti",conteggio);
        m.put("tempoConnessioneUtente",tempoConnessione);
        tempo=System.currentTimeMillis();
        long conteggioAutomobili=automobileRepo.count();
        long tempoConnessioneAutomobile=System.currentTimeMillis()-tempo;
        m.put("numeroAutomobili",conteggioAutomobili);
        m.put("tempoConnessioneAutomobile",tempoConnessioneAutomobile);
        tempo=System.currentTimeMillis();
        long conteggioCasa=casaAutomobilisticaRepo.count();
        long tempoConnessioneCasa=System.currentTimeMillis()-tempo;
        m.put("numeroCasaAutomobilistica",conteggioCasa);
        m.put("tempoConnessioneCasaAutomobilistica",tempoConnessioneCasa);
        return m;
    }

    @ReadOperation
    public Map<String,Object> getInfo2(@Selector String tipoDiConnessione){
        Map<String,Object> m= new HashMap<>();

        long tempo=System.currentTimeMillis();
        long conteggio=switch (tipoDiConnessione){
            case "utente" ->repo.count();
            case "automobile" ->automobileRepo.count();
            case "casa" -> casaAutomobilisticaRepo.count();
            default -> 0;
        };

        long tempoConnessione=System.currentTimeMillis()-tempo;
        m.put("numero"+tipoDiConnessione,conteggio);
        m.put("tempoConnessione"+tipoDiConnessione,tempoConnessione);
        return m;
    }
}
