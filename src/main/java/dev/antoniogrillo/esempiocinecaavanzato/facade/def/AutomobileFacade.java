package dev.antoniogrillo.esempiocinecaavanzato.facade.def;

import dev.antoniogrillo.esempiocinecaavanzato.dto.response.AutomobileDTO;
import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;

import java.util.List;

public interface AutomobileFacade {
    List<AutomobileDTO> getAutomobiliPerUtente(Utente u);

    List<AutomobileDTO> getAutomobiliPerAnnoMinimo(int annoMinimo);

    List<AutomobileDTO> getAutomobiliPerMarca(long id);

    AutomobileDTO getAutomobile(long id);

    void assegnaAutoAllUtente(long id, Utente u);
}
