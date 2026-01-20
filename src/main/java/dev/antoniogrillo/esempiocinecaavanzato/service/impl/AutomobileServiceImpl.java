package dev.antoniogrillo.esempiocinecaavanzato.service.impl;

import dev.antoniogrillo.esempiocinecaavanzato.dto.response.AutomobileDTO;
import dev.antoniogrillo.esempiocinecaavanzato.mapper.AutomobileMapper;
import dev.antoniogrillo.esempiocinecaavanzato.model.Automobile;
import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;
import dev.antoniogrillo.esempiocinecaavanzato.repository.AutomobileRepository;
import dev.antoniogrillo.esempiocinecaavanzato.repository.AutomobiliCriteraRepository;
import dev.antoniogrillo.esempiocinecaavanzato.service.def.AutomobileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutomobileServiceImpl implements AutomobileService {

    private final AutomobileRepository repo;
    private final AutomobiliCriteraRepository repository;
    private final AutomobileMapper mapper;

    @Override
    public AutomobileDTO getAutomobile(long id) {
        Automobile a= repo.findById(id).orElse(null);
        return a==null?null:mapper.toAutomobileDTO(a);
    }

    @Override
    public List<AutomobileDTO> getAutomobiliPerUtente(Utente u) {
        List<Automobile> l=repo.findAllByProprietari_id(u.getId());
        return mapper.toAutomobileDTO(l);
    }

    @Override
    public List<AutomobileDTO> getAutomobiliPerAnnoMinimo(int annoMinimo) {
        List<Automobile> l=repo.findAllByAnnoImmatricolazioneAfterOrderByAnnoImmatricolazione(annoMinimo);
        return mapper.toAutomobileDTO(l);
    }

    @Override
    public List<AutomobileDTO> getAutomobiliPerMarca(long id) {
        List<Automobile> l=repo.findAllByMarca_Id(id);
        return mapper.toAutomobileDTO(l);
    }

//    @Override
//    public AutomobileDTO getAutomobile(long id) {
//        Automobile a= repository.getAutomobiliById(id);
//        return mapper.toAutomobileDTO(a);
//    }
//
//    @Override
//    public List<AutomobileDTO> getAutomobiliPerUtente(Utente u) {
//        List<Automobile> l= repository.getAutomobiliByUtente(u);
//        return mapper.toAutomobileDTO(l);
//    }
//
//    @Override
//    public List<AutomobileDTO> getAutomobiliPerAnnoMinimo(int annoMinimo) {
//        List<Automobile> l= repository.getAutomobiliByAnnoMinimo(annoMinimo);
//        return mapper.toAutomobileDTO(l);
//    }
//
//    @Override
//    public List<AutomobileDTO> getAutomobiliPerMarca(long id) {
//        List<Automobile> l= repository.getAutomobiliByMarca(id);
//        return mapper.toAutomobileDTO(l);
//    }
}
