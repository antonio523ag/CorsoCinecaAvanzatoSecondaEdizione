package dev.antoniogrillo.esempiocinecaavanzato.controller;

import dev.antoniogrillo.esempiocinecaavanzato.model.Automobile;
import dev.antoniogrillo.esempiocinecaavanzato.model.CasaAutomobilistica;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

@RestController
@RequiredArgsConstructor
public class PrimoController {


    private final ObjectMapper objectMapper;

//    public PrimoController(ObjectMapper objectMapper) {
//        this.objectMapper=objectMapper;
//    }

    @RequestMapping("/primo")
    public String getPrimo(){
        return "ciao";
    }

    //@RequestMapping(method = RequestMethod.POST,value = "/primoResponse")
    @PostMapping("/primoResponse")
    public ResponseEntity<String> getPrimoResponse(){
        //return ResponseEntity.badRequest().body("ciao");
        //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ciao");
        return new ResponseEntity<>("ciao", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/automobile")
    public ResponseEntity<Automobile> getAutomobile(){
        return new ResponseEntity<>(new Automobile(1,new CasaAutomobilistica(),"Panda",1974,"Verde",null,1), HttpStatus.OK);
    }

    public ResponseEntity<String> responseJson(){
        Automobile automobile = new Automobile(1,new CasaAutomobilistica(),"Panda",1974,"Verde",null,1);
        return new ResponseEntity<>(objectMapper.writeValueAsString(automobile), HttpStatus.OK);
    }

    public ResponseEntity<Void> responseVoid(@RequestBody Automobile automobile){
        return ResponseEntity.ok().build();
    }

    //http://localhost:8080/saluta/Antonio/Grillo/parametroACaso
    @GetMapping("/saluta/{nome}/{surname}/parametroACaso")
    public ResponseEntity<String> salutaPath(@PathVariable String nome,@PathVariable("surname") String cognome){
        return ResponseEntity.ok("Ciao "+nome+" "+cognome);
    }


    //http://localhost:8080/saluta?nome=Antonio&surname=Grillo
    @GetMapping("/saluta")
    public ResponseEntity<String> salutaQuery(@RequestParam String nome,@RequestParam("surname") String cognome){
        return ResponseEntity.ok("Ciao "+nome+" "+cognome);
    }






}
