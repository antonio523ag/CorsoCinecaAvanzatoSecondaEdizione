package dev.antoniogrillo.esempiocinecaavanzato.controller;

import dev.antoniogrillo.esempiocinecaavanzato.dto.request.LoginDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.request.RegistraUtenteDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.response.LoginResponseDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.response.UtenteDTO;
import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;
import dev.antoniogrillo.esempiocinecaavanzato.service.def.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UtenteController {

    private final UtenteService service;

    @PostMapping("/all/utente")
    public ResponseEntity<Void> registraUtente(@RequestBody RegistraUtenteDTO dto){
        boolean risultato=service.registraUtente(dto);
        if(risultato){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/all/utenti")
    public ResponseEntity<List<UtenteDTO>> getAllUtenti(){
        return ResponseEntity.ok(service.getAllUtenti());
    }


    @PostMapping("/all/login")
    public ResponseEntity<UtenteDTO> login(@RequestBody LoginDTO request){
        LoginResponseDTO u=service.login(request);
        if(u!=null){
            return ResponseEntity.status(HttpStatus.OK).header("Authorization",u.token()).body(u.utente());
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/all/utente/autenticato")
    public Utente get(@AuthenticationPrincipal Utente u){
        return u;
    }

}
