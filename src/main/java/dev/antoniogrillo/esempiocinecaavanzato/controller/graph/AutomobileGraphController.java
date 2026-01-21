package dev.antoniogrillo.esempiocinecaavanzato.controller.graph;

import dev.antoniogrillo.esempiocinecaavanzato.dto.response.AutomobileDTO;
import dev.antoniogrillo.esempiocinecaavanzato.facade.def.GraphAutomobileFacade;
import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AutomobileGraphController {

    private final GraphAutomobileFacade facade;

    /*
    getAutomobile(id:ID!):Automobile!
    getAutomobiliPerUtente:[Automobile]
    getAutomobiliPerAnnoMinimo(input:Int!):[Automobile]
    getAutomobiliPerMarca(input:ID!):[Automobile]
     */

    public AutomobileDTO getAutomobile(long id){
        return facade.getAutomobile(id);
    }

    public List<AutomobileDTO> getAutomobiliPerUtente(@AuthenticationPrincipal Utente utente){
        return facade.getAutomobiliPerUtente(utente);
    }

    public List<AutomobileDTO> getAutomobiliPerAnnoMinimo(int input){
        return facade.getAutomobiliPerAnnoMinimo(input);
    }

    public List<AutomobileDTO> getAutomobiliPerMarca(long input){
        return facade.getAutomobiliPerMarca(input);
    }
}
