package dev.antoniogrillo.esempiocinecaavanzato.facade.impl;

import dev.antoniogrillo.esempiocinecaavanzato.dto.request.GraphLoginResponseDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.request.LoginDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.request.RegistraUtenteDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.response.AutomobileDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.response.LoginResponseDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.response.UtenteDTO;
import dev.antoniogrillo.esempiocinecaavanzato.exception.GraphQlCustomExcpetion;
import dev.antoniogrillo.esempiocinecaavanzato.facade.def.GraphUtenteFacade;
import dev.antoniogrillo.esempiocinecaavanzato.mapper.AutomobileMapper;
import dev.antoniogrillo.esempiocinecaavanzato.mapper.UtenteMapper;
import dev.antoniogrillo.esempiocinecaavanzato.model.Automobile;
import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;
import dev.antoniogrillo.esempiocinecaavanzato.service.def.AutomobileService;
import dev.antoniogrillo.esempiocinecaavanzato.service.def.GestoreTokenService;
import dev.antoniogrillo.esempiocinecaavanzato.service.def.UtenteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GraphUtenteFacadeImpl implements GraphUtenteFacade {

    private final UtenteService utenteService;
    private final UtenteMapper mapper;
    private final AutomobileService automobileService;
    private final GestoreTokenService gestoreTokenService;

    @Override
    public List<Utente> getAllUtenti() {
        List<Utente> l=utenteService.getAllUtenti();
        return l;
    }

    @Override
    @Transactional
    public GraphLoginResponseDTO login(LoginDTO input) {
        Utente u=utenteService.login(input.username(),input.password()).orElse(null);
        if(u!=null) return new GraphLoginResponseDTO(gestoreTokenService.generaToken(u),u);
        throw new GraphQlCustomExcpetion(ErrorType.UNAUTHORIZED,"Credenziali non valide");
    }

    @Override
    public boolean registraUtente(RegistraUtenteDTO input) {
        Utente u=mapper.toUtente(input);
        return utenteService.registraUtente(u);
    }

    @Override
    public Utente convertiUtente(Utente u) {
        if(u!=null) return u;
        throw new GraphQlCustomExcpetion(ErrorType.UNAUTHORIZED,"Utente non trovato");
    }

    @Override
    public List<Automobile> getAutomobiliPerUtente(Utente utente) {
        Utente u=utenteService.getById(utente.getId()).orElse(null);
       if(u!=null) return automobileService.getAutomobiliPerUtente(u);
       throw new GraphQlCustomExcpetion(ErrorType.UNAUTHORIZED,"Utente non trovato");
    }
}
