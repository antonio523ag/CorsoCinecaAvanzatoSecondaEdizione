package dev.antoniogrillo.esempiocinecaavanzato.controller.rest;

import dev.antoniogrillo.esempiocinecaavanzato.dto.response.AutomobileDTO;
import dev.antoniogrillo.esempiocinecaavanzato.facade.def.AutomobileFacade;
import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AutomobileController {

    private final AutomobileFacade facade;

    @GetMapping("/all/automobile/{id}")
    public ResponseEntity<AutomobileDTO> getAutomobile(long id){
        return ResponseEntity.ok(facade.getAutomobile(id));
    }

    @GetMapping("/authorized/automobile")
    public ResponseEntity<List<AutomobileDTO>> getAutomobiliPerUtente(@AuthenticationPrincipal Utente u){
        return ResponseEntity.ok(facade.getAutomobiliPerUtente(u));
    }

    @GetMapping("/all/automobile/anno/min/{annoMinimo}")
    public ResponseEntity<List<AutomobileDTO>> getAutomobiliPerAnnoMinimo(@PathVariable int annoMinimo){
        return ResponseEntity.ok(facade.getAutomobiliPerAnnoMinimo(annoMinimo));
    }

    @GetMapping("/all/automobile/marca/{id}")
    public ResponseEntity<List<AutomobileDTO>> getAutomobiliPerMarca(@PathVariable long id){
        return ResponseEntity.ok(facade.getAutomobiliPerMarca(id));
    }

    @PostMapping("/authenticated/utente/assegnaAuto/{id}")
    public ResponseEntity<Void> assegnaAutoAllUtente(@PathVariable long id,@AuthenticationPrincipal Utente u){
        facade.assegnaAutoAllUtente(id,u);
        return ResponseEntity.ok().build();
    }


}
