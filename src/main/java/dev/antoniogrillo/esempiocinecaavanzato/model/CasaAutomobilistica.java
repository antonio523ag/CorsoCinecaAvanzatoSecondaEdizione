package dev.antoniogrillo.esempiocinecaavanzato.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
    @Min(value = 0,message = "Id non valido")
    private long id;
    @NotBlank(message = "Nome non può essere vuoto")
    private String nome;
    @OneToMany(mappedBy = "marca",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE})
    private List<Automobile> automobili;
}
