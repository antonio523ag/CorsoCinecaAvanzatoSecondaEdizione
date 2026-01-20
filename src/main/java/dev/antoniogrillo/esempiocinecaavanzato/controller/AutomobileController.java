package dev.antoniogrillo.esempiocinecaavanzato.controller;

import dev.antoniogrillo.esempiocinecaavanzato.dto.response.AutomobileDTO;
import dev.antoniogrillo.esempiocinecaavanzato.model.Automobile;
import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;
import dev.antoniogrillo.esempiocinecaavanzato.service.def.AutomobileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AutomobileController {

    private final AutomobileService service;

    @GetMapping("/all/automobile/{id}")
    public ResponseEntity<AutomobileDTO> getAutomobile(long id){
        return ResponseEntity.ok(service.getAutomobile(id));
    }

    @GetMapping("/authorized/automobile")
    public ResponseEntity<List<AutomobileDTO>> getAutomobiliPerUtente(@AuthenticationPrincipal Utente u){
        return ResponseEntity.ok(service.getAutomobiliPerUtente(u));
    }

    @GetMapping("/all/automobile/anno/min/{annoMinimo}")
    public ResponseEntity<List<AutomobileDTO>> getAutomobiliPerAnnoMinimo(@PathVariable int annoMinimo){
        return ResponseEntity.ok(service.getAutomobiliPerAnnoMinimo(annoMinimo));
    }

    @GetMapping("/all/automobile/marca/{id}")
    public ResponseEntity<List<AutomobileDTO>> getAutomobiliPerMarca(@PathVariable long id){
        return ResponseEntity.ok(service.getAutomobiliPerMarca(id));
    }


}
