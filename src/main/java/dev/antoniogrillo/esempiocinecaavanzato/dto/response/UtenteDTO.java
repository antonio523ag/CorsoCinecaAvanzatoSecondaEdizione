package dev.antoniogrillo.esempiocinecaavanzato.dto.response;

import lombok.ToString;

import java.util.List;

public record UtenteDTO(long id, String nome, String cognome,String username, List<AutomobileDTO> automobili) {
}
