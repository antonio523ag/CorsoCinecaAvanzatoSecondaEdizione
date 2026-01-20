package dev.antoniogrillo.esempiocinecaavanzato.service.impl;

import dev.antoniogrillo.esempiocinecaavanzato.dto.request.LoginDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.request.RegistraUtenteDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.response.LoginResponseDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.response.UtenteDTO;
import dev.antoniogrillo.esempiocinecaavanzato.mapper.UtenteMapper;
import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;
import dev.antoniogrillo.esempiocinecaavanzato.repository.UtenteRepository;
import dev.antoniogrillo.esempiocinecaavanzato.service.def.GestoreTokenService;
import dev.antoniogrillo.esempiocinecaavanzato.service.def.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtenteServiceImpl implements UtenteService {

    private final UtenteRepository repo;
    private final UtenteMapper mapper;
    private final GestoreTokenService gestoreTokenService;

    @Override
    public boolean registraUtente(RegistraUtenteDTO dto) {
        Utente u= mapper.toUtente(dto);
        u=repo.save(u);
        return u.getId()!=0;
    }

    @Override
    public List<UtenteDTO> getAllUtenti() {
        List<Utente> utenti=repo.findAll();
        return mapper.toUtenteDTO(utenti);
    }

    @Override
    public LoginResponseDTO login(LoginDTO request) {
        Utente utente=repo.findByEmailAndPassword(request.username(),request.password())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Utente non trovato"));
        UtenteDTO dto= mapper.toUtenteDTO(utente);
        String token=gestoreTokenService.generaToken(utente);
        return new LoginResponseDTO(dto,token);
    }
}
