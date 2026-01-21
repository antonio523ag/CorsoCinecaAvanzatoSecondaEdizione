package dev.antoniogrillo.esempiocinecaavanzato.controller.graph;

import dev.antoniogrillo.esempiocinecaavanzato.dto.request.GraphLoginResponseDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.request.LoginDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.request.RegistraUtenteDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.response.AutomobileDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.response.LoginResponseDTO;
import dev.antoniogrillo.esempiocinecaavanzato.dto.response.UtenteDTO;
import dev.antoniogrillo.esempiocinecaavanzato.facade.def.GraphUtenteFacade;
import dev.antoniogrillo.esempiocinecaavanzato.model.Automobile;
import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
@EnableMethodSecurity
public class UtenteGraphController {

    private final GraphUtenteFacade facade;

    /*
     getAllUtenti:[Utente]
    login(input:LoginDTO!):LoginResponseDTO!
    getUtente:Utente!
    registraUtente(input:RegistraUtenteDTO):Boolean
     */

    @QueryMapping
    public List<Utente> getAllUtenti(){
       return facade.getAllUtenti();
    }

    @QueryMapping
    public GraphLoginResponseDTO login(@Argument LoginDTO input){
        return facade.login(input);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('UTENTE')")
    @QueryMapping
    public Utente getUtente(@AuthenticationPrincipal Utente u){
        return facade.convertiUtente(u);
    }

    @MutationMapping
    public boolean registraUtente(@Argument RegistraUtenteDTO input){
        return facade.registraUtente(input);
    }



    @SchemaMapping
    public List<Automobile> getAutomobili(Utente utente){
        return facade.getAutomobiliPerUtente(utente);
    }
}
