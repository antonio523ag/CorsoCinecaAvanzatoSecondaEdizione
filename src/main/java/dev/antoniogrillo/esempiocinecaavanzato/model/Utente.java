package dev.antoniogrillo.esempiocinecaavanzato.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @Min(value = 0,message = "Id non valido")
    private long id;
    @NotBlank(message = "Email non può essere vuota")
    @Email(message = "Email non valida")
    @Column(name = "username")
    private String email;
    @NotBlank(message = "Nome non può essere vuoto")
    private String nome;
    @NotBlank(message = "Cognome non può essere vuoto")
    private String cognome;
    @NotNull(message = "Password non può essere nulla")
    @Size(min = 4,max = 20,message = "La password deve essere compresa tra 4 e 20 caratteri")
    private String password;
    @NotNull(message = "Ruolo non può essere nullo")
    private Ruolo ruolo;

    @ManyToMany(fetch = FetchType.EAGER)
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
