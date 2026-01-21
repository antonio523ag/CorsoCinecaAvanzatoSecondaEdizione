package dev.antoniogrillo.esempiocinecaavanzato.service.def;

import dev.antoniogrillo.esempiocinecaavanzato.dto.response.AutomobileDTO;
import dev.antoniogrillo.esempiocinecaavanzato.model.Automobile;
import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;

import java.util.List;
import java.util.Optional;

public interface AutomobileService {
    Optional<Automobile> getAutomobile(long id);

    List<Automobile> getAutomobiliPerUtente(Utente u);

    List<Automobile> getAutomobiliPerAnnoMinimo(int annoMinimo);

    List<Automobile> getAutomobiliPerMarca(long id);
}
