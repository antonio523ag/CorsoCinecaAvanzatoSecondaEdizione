package dev.antoniogrillo.esempiocinecaavanzato.service.def;

import dev.antoniogrillo.esempiocinecaavanzato.dto.request.LoginDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.request.RegistraUtenteDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.response.LoginResponseDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.response.UtenteDTO;

import java.util.List;

public interface UtenteService {
    boolean registraUtente(RegistraUtenteDTO dto);

    List<UtenteDTO> getAllUtenti();

    LoginResponseDTO login(LoginDTO request);
}
