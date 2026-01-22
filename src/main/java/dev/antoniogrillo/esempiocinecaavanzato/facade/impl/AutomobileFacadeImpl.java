package dev.antoniogrillo.esempiocinecaavanzato.facade.impl;

import dev.antoniogrillo.esempiocinecaavanzato.dto.response.AutomobileDTO;
import dev.antoniogrillo.esempiocinecaavanzato.facade.def.AutomobileFacade;
import dev.antoniogrillo.esempiocinecaavanzato.mapper.AutomobileMapper;
import dev.antoniogrillo.esempiocinecaavanzato.model.Automobile;
import dev.antoniogrillo.esempiocinecaavanzato.model.CasaAutomobilistica;
import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;
import dev.antoniogrillo.esempiocinecaavanzato.service.def.AutomobileService;
import dev.antoniogrillo.esempiocinecaavanzato.service.def.CasaAutomobilisticaService;
import dev.antoniogrillo.esempiocinecaavanzato.service.def.UtenteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AutomobileFacadeImpl implements AutomobileFacade {

    private final AutomobileService service;
    private final AutomobileMapper mapper;
    private final UtenteService utenteService;
    private final CasaAutomobilisticaService casaAutomobilisticaService;

    @Override
    @Transactional
    public List<AutomobileDTO> getAutomobiliPerUtente(Utente u) {
        if(utenteService.getById(u.getId()).isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Utente non trovato");
        List<Automobile> l= service.getAutomobiliPerUtente(u);
        return mapper.toAutomobileDTO(l);
    }

    @Override
    public List<AutomobileDTO> getAutomobiliPerAnnoMinimo(int annoMinimo) {
        if(annoMinimo>LocalDate.now().getYear()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Anno minimo non valido");
        List<Automobile> l= service.getAutomobiliPerAnnoMinimo(annoMinimo);
        return mapper.toAutomobileDTO(l);
    }

    @Override
    @Transactional
    public List<AutomobileDTO> getAutomobiliPerMarca(long id) {
        CasaAutomobilistica c=casaAutomobilisticaService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Casa non trovata"));
        return mapper.toAutomobileDTO(service.getAutomobiliPerMarca(c.getId()));
    }

    @Override
    public AutomobileDTO getAutomobile(long id) {
        Automobile a=service.getAutomobile(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Automobile non trovata"));

        return mapper.toAutomobileDTO(a);
    }

    @Override
    public void assegnaAutoAllUtente(long id, Utente u) {
        u=utenteService.getById(u.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Utente non trovato"));
        Automobile a=service.getAutomobile(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Automobile non trovata"));
        if(u.getAutomobili()==null)u.setAutomobili(new ArrayList<>());
        if(a.getProprietari()==null)a.setProprietari(new ArrayList<>());
        if(u.getAutomobili().stream().map(Automobile::getId).anyMatch(p->p==id)) return;
        u.getAutomobili().add(a);
        a.getProprietari().add(u);
        utenteService.save(u);
    }
}
