package dev.antoniogrillo.esempiocinecaavanzato.service.criteria;

import dev.antoniogrillo.esempiocinecaavanzato.model.Automobile;
import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;
import dev.antoniogrillo.esempiocinecaavanzato.repository.AutomobiliCriteraRepository;
import dev.antoniogrillo.esempiocinecaavanzato.service.def.AutomobileService;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutomobileServiceImpl implements AutomobileService {

    private final AutomobiliCriteraRepository repo;

    @Override
    public Optional<Automobile> getAutomobile(long id) {
        try {
            Automobile a=repo.getAutomobiliById(id);
            return Optional.of(a);
        }catch (NoResultException e){
            return Optional.empty();
        }

    }

    @Override
    public List<Automobile> getAutomobiliPerUtente(Utente u) {
        return repo.getAutomobiliByUtente(u);
    }

    @Override
    public List<Automobile> getAutomobiliPerAnnoMinimo(int annoMinimo) {
        return repo.getAutomobiliByAnnoMinimo(annoMinimo);
    }

    @Override
    public List<Automobile> getAutomobiliPerMarca(long id) {
        return repo.getAutomobiliByMarca(id);
    }
}
