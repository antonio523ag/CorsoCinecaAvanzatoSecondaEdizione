package dev.antoniogrillo.esempiocinecaavanzato.service.def;

import dev.antoniogrillo.esempiocinecaavanzato.model.CasaAutomobilistica;

import java.util.Optional;

public interface CasaAutomobilisticaService {
    Optional<CasaAutomobilistica> findById(long id);
}
