package dev.antoniogrillo.esempiocinecaavanzato.facade.def;

import dev.antoniogrillo.esempiocinecaavanzato.dto.request.GraphLoginResponseDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.request.LoginDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.request.RegistraUtenteDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.response.AutomobileDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.response.LoginResponseDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.response.UtenteDTO;
import dev.antoniogrillo.esempiocinecaavanzato.model.Automobile;
import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;

import java.util.List;

public interface GraphUtenteFacade {
    List<Utente> getAllUtenti();

    GraphLoginResponseDTO login(LoginDTO input);

    boolean registraUtente(RegistraUtenteDTO input);

    Utente convertiUtente(Utente u);

    List<Automobile> getAutomobiliPerUtente(Utente utente);
}
