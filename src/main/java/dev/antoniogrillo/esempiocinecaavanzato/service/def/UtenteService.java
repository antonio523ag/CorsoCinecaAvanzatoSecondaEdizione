package dev.antoniogrillo.esempiocinecaavanzato.service.def;

import dev.antoniogrillo.esempiocinecaavanzato.dto.request.LoginDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.request.RegistraUtenteDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.response.LoginResponseDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.response.UtenteDTO;
import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
public interface UtenteService {
    boolean registraUtente(@Valid Utente dto);

    List<Utente> getAllUtenti();

    Page<Utente> getAllUtenti(int pagina, int dimensionePagina);

    Optional<Utente> login(
            @NotNull(message = "devi inserire una email")
            @Email(message = "email non valida")
            String email,String password);

    Optional<Utente> findByEmail(
            @NotNull(message = "devi inserire una email")
                    @Email(message = "email non valida")
            String email);

    Optional<Utente> getById(
            @Min(value = 1,message = "Id non valido")
            long id);
}
