package dev.antoniogrillo.esempiocinecaavanzato.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginDTO(
        @NotNull(message = "Username non può essere vuoto")
        @Email(message = "Username deve essere un indirizzo email valido")
        String username,
        @NotNull(message = "Password non può essere vuota")
        @Size(min = 4,max = 20,message = "La password deve essere compresa tra 4 e 20 caratteri")
        String password) {
}
