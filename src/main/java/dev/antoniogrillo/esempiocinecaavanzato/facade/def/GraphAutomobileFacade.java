package dev.antoniogrillo.esempiocinecaavanzato.facade.def;

import dev.antoniogrillo.esempiocinecaavanzato.dto.response.AutomobileDTO;
import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;

import java.util.List;

public interface GraphAutomobileFacade {
    AutomobileDTO getAutomobile(long id);

    List<AutomobileDTO> getAutomobiliPerUtente(Utente utente);

    List<AutomobileDTO> getAutomobiliPerAnnoMinimo(int input);

    List<AutomobileDTO> getAutomobiliPerMarca(long input);
}
