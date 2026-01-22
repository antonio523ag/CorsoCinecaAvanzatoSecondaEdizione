package dev.antoniogrillo.esempiocinecaavanzato.controller.rest;

import dev.antoniogrillo.esempiocinecaavanzato.dto.request.LoginDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.request.RegistraUtenteDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.response.LoginResponseDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.response.UtenteDTO;
import dev.antoniogrillo.esempiocinecaavanzato.facade.def.UtenteFacade;
import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger logger= LogManager.getLogger(UtenteController.class);

    private final UtenteFacade facade;

    @PostMapping("/all/utente")
    public ResponseEntity<Void> registraUtente(@RequestBody RegistraUtenteDTO dto){
        boolean risultato=facade.registraUtente(dto);
        if(risultato){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/all/utenti")
    public ResponseEntity<List<UtenteDTO>> getAllUtenti(){
        return ResponseEntity.ok(facade.getAllUtenti());
    }


    @PostMapping("/all/login")
    public ResponseEntity<UtenteDTO> login(@RequestBody LoginDTO request){
        logger.debug("Login request received for user: {}", request.username());
        LoginResponseDTO u=facade.login(request);
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
