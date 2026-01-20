package dev.antoniogrillo.esempiocinecaavanzato.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "casa_automobilistica")
public class CasaAutomobilistica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    @OneToMany(mappedBy = "marca",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE})
    private List<Automobile> automobili;
}
