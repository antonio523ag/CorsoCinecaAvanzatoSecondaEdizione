package dev.antoniogrillo.esempiocinecaavanzato.service.impl;

import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;
import dev.antoniogrillo.esempiocinecaavanzato.repository.UtenteRepository;
import dev.antoniogrillo.esempiocinecaavanzato.service.def.GestoreTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class GestoreTokenServiceImpl implements GestoreTokenService {

    private final UtenteRepository repo;

    @Value("${custom.tag.jwt.chiave}")
    private String chiave;

    @Value("${custom.tag.jwt.durata}")
    private long durata;

    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(chiave));
    }




    @Override
    public String generaToken(Utente u) {
        return Jwts.builder()
                .claims()
                .subject(u.getEmail())
                .expiration(new Date(System.currentTimeMillis() + durata))
                .issuedAt(new Date(System.currentTimeMillis()))
                .add("ruolo",u.getRuolo())
                .add("nome",u.getNome()+" "+u.getCognome())
                .and()
                .signWith(getKey())
                .compact();
    }

    @Override
    public Utente recuperaUtenteDaToken(String token) {
        if(getScadenza(token).isAfter(LocalDate.now())) {
            String email=getEmail(token);
            return repo.findByEmail(email).orElse(null);
        }else return null;
    }

    private Claims getClaims(String token){
        if(token.startsWith("Bearer ")){token=token.substring(7);}
        return (Claims) Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parse(token).getPayload();
    }

    private String getEmail(String token){
        return getClaims(token).getSubject();
    }

    private String getRuolo(String token){
        return getClaims(token).get("ruolo").toString();
    }

    private LocalDate getScadenza(String token){
        Date scadenza=getClaims(token).getExpiration();
        return LocalDate.ofInstant(scadenza.toInstant(),java.time.ZoneId.systemDefault());
    }
}
