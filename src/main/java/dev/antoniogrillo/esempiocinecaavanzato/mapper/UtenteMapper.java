package dev.antoniogrillo.esempiocinecaavanzato.mapper;

import dev.antoniogrillo.esempiocinecaavanzato.dto.request.RegistraUtenteDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.response.AutomobileDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.response.UtenteDTO;
import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UtenteMapper {

    private final AutomobileMapper mapper;

    public UtenteDTO toUtenteDTO(Utente utente){
        List<AutomobileDTO> automobili=mapper.toAutomobileDTO(utente.getAutomobili());
        return new UtenteDTO(utente.getId(),
                utente.getNome(),
                utente.getCognome(),
                utente.getEmail(),
                automobili);
    }

    public List<UtenteDTO> toUtenteDTO(List<Utente> utenti){
        return utenti==null?new ArrayList<>():utenti.stream()
                                                            .map(this::toUtenteDTO)
                                                            .toList();
    }

    public Utente toUtente(RegistraUtenteDTO dto){
        Utente utente=new Utente();
        utente.setNome(dto.nome());
        utente.setCognome(dto.cognome());
        utente.setEmail(dto.username());
        utente.setPassword(dto.password());
        return utente;
    }
}
