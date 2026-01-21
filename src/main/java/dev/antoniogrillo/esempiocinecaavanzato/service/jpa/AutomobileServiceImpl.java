package dev.antoniogrillo.esempiocinecaavanzato.service.jpa;

import dev.antoniogrillo.esempiocinecaavanzato.model.Automobile;
import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;
import dev.antoniogrillo.esempiocinecaavanzato.repository.AutomobileRepository;
import dev.antoniogrillo.esempiocinecaavanzato.service.def.AutomobileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class AutomobileServiceImpl implements AutomobileService {

    private final AutomobileRepository repo;

    @Override
    public Optional<Automobile> getAutomobile(long id) {
        return repo.findById(id);
    }

    @Override
    public List<Automobile> getAutomobiliPerUtente(Utente u) {
        return repo.findAllByProprietari_id(u.getId());
    }

    @Override
    public List<Automobile> getAutomobiliPerAnnoMinimo(int annoMinimo) {
        return repo.findAllByAnnoImmatricolazioneAfterOrderByAnnoImmatricolazione(annoMinimo);
    }

    @Override
    public List<Automobile> getAutomobiliPerMarca(long id) {
        return repo.findAllByMarca_Id(id);
    }

}
