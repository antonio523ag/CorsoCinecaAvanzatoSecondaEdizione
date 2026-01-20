package dev.antoniogrillo.esempiocinecaavanzato.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
public class Utente implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "username")
    private String email;
    private String nome;
    private String cognome;
    private String password;
    private Ruolo ruolo;

    @ManyToMany
    @JoinTable(name = "utente_automobile",
                        joinColumns = @JoinColumn(name = "utente_id",unique = true),
                        inverseJoinColumns = @JoinColumn(name = "automobile_id",unique = true),
                uniqueConstraints = @UniqueConstraint(name = "utente_automobile_unique",columnNames = {"utente_id","automobile_id"}))
    private List<Automobile> automobili;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+ruolo.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }
}
