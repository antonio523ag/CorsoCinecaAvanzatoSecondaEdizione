package dev.antoniogrillo.esempiocinecaavanzato.facade.def;

import dev.antoniogrillo.esempiocinecaavanzato.dto.request.LoginDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.request.RegistraUtenteDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.response.LoginResponseDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.response.UtenteDTO;

import java.util.List;

public interface UtenteFacade {
    boolean registraUtente(RegistraUtenteDTO dto);

    List<UtenteDTO> getAllUtenti();

    LoginResponseDTO login(LoginDTO request);
}
