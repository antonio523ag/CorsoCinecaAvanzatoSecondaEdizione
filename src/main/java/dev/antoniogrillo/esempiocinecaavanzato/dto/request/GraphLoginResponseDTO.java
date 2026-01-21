package dev.antoniogrillo.esempiocinecaavanzato.dto.request;

import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;

public record GraphLoginResponseDTO(String token, Utente utente) {
}
