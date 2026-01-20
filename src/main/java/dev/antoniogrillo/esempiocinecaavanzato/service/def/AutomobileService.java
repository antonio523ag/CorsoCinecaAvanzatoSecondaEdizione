package dev.antoniogrillo.esempiocinecaavanzato.service.def;

import dev.antoniogrillo.esempiocinecaavanzato.dto.response.AutomobileDTO;
import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;

import java.util.List;

public interface AutomobileService {
    AutomobileDTO getAutomobile(long id);

    List<AutomobileDTO> getAutomobiliPerUtente(Utente u);

    List<AutomobileDTO> getAutomobiliPerAnnoMinimo(int annoMinimo);

    List<AutomobileDTO> getAutomobiliPerMarca(long id);
}
