package dev.antoniogrillo.esempiocinecaavanzato.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
//@Getter
//@Setter
//@RequiredArgsConstructor
//@EqualsAndHashCode(callSuper = false)
//@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Automobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 0,message = "Id non valido")
    private long id;
    @ManyToOne
    @JoinColumn(name = "casa_automobilistica_id",nullable = false)
    @NotNull(message = "Marca non può essere nulla")
    private CasaAutomobilistica marca;
    @NotNull(message = "Modello non può essere vuoto")
    private String modello;
    @Column(name = "anno_immatricolazione",nullable = false)
    @Min(value = 1900,message = "Anno immatricolazione non valido")
    private int annoImmatricolazione;
    @NotNull(message = "Colore non può essere vuoto")
    private String colore;
    @ManyToMany(mappedBy = "automobili")
    private List<Utente> proprietari;

    @Transient
    private int anni;
}
