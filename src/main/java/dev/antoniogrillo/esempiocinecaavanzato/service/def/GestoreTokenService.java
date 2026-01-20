package dev.antoniogrillo.esempiocinecaavanzato.service.def;

import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;

public interface GestoreTokenService {

    String generaToken(Utente u);
    Utente recuperaUtenteDaToken(String token);
}
