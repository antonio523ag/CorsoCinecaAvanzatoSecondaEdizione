package dev.antoniogrillo.esempiocinecaavanzato.facade.impl;

import dev.antoniogrillo.esempiocinecaavanzato.dto.request.LoginDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.request.RegistraUtenteDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.response.LoginResponseDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.response.UtenteDTO;
import dev.antoniogrillo.esempiocinecaavanzato.facade.def.UtenteFacade;
import dev.antoniogrillo.esempiocinecaavanzato.mapper.UtenteMapper;
import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;
import dev.antoniogrillo.esempiocinecaavanzato.service.def.GestoreTokenService;
import dev.antoniogrillo.esempiocinecaavanzato.service.def.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtenteFacadeImpl implements UtenteFacade {

    private final UtenteService service;
    private final UtenteMapper mapper;
    private final GestoreTokenService gestoreTokenService;

    @Override
    public boolean registraUtente(RegistraUtenteDTO dto) {
        Utente u=mapper.toUtente(dto);
        if(service.registraUtente(u))return true;
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Utente già esistente");
    }

    @Override
    public List<UtenteDTO> getAllUtenti() {
        List<Utente> l=service.getAllUtenti();
        return mapper.toUtenteDTO(l);
    }

    @Override
    public LoginResponseDTO login(LoginDTO request) {
        Utente u=service.login(request.username(),request.password()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Credenziali non valide"));
        String token=gestoreTokenService.generaToken(u);
        UtenteDTO utenteDTO=mapper.toUtenteDTO(u);
        return new LoginResponseDTO(utenteDTO,token);
    }
}
