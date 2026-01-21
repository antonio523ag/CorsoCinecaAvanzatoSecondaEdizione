package dev.antoniogrillo.esempiocinecaavanzato.facade.impl;

import dev.antoniogrillo.esempiocinecaavanzato.dto.response.AutomobileDTO;
import dev.antoniogrillo.esempiocinecaavanzato.facade.def.GraphAutomobileFacade;
import dev.antoniogrillo.esempiocinecaavanzato.mapper.AutomobileMapper;
import dev.antoniogrillo.esempiocinecaavanzato.model.Automobile;
import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;
import dev.antoniogrillo.esempiocinecaavanzato.service.def.AutomobileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GraphAutomobileFacadeImpl implements GraphAutomobileFacade {

    private final AutomobileService service;
    private final AutomobileMapper mapper;

    @Override
    public AutomobileDTO getAutomobile(long id) {
        Automobile a=service.getAutomobile(id).orElse(null);
        if(a!=null) return mapper.toAutomobileDTO(a);
        return null;
    }

    @Override
    public List<AutomobileDTO> getAutomobiliPerUtente(Utente utente) {
        List<Automobile> l=service.getAutomobiliPerUtente(utente);
        return mapper.toAutomobileDTO(l);
    }

    @Override
    public List<AutomobileDTO> getAutomobiliPerAnnoMinimo(int input) {
        List<Automobile> l=service.getAutomobiliPerAnnoMinimo(input);
        return mapper.toAutomobileDTO(l);
    }

    @Override
    public List<AutomobileDTO> getAutomobiliPerMarca(long input) {
        List<Automobile> l=service.getAutomobiliPerMarca(input);
        return mapper.toAutomobileDTO(l);
    }
}
