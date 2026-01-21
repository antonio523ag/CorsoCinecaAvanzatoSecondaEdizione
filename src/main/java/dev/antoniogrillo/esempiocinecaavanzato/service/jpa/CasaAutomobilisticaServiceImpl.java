package dev.antoniogrillo.esempiocinecaavanzato.service.jpa;

import dev.antoniogrillo.esempiocinecaavanzato.model.CasaAutomobilistica;
import dev.antoniogrillo.esempiocinecaavanzato.repository.CasaAutomobilisticaRepository;
import dev.antoniogrillo.esempiocinecaavanzato.service.def.CasaAutomobilisticaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CasaAutomobilisticaServiceImpl implements CasaAutomobilisticaService {

    private final CasaAutomobilisticaRepository repo;

    @Override
    public Optional<CasaAutomobilistica> findById(long id) {
        return repo.findById(id);
    }
}
