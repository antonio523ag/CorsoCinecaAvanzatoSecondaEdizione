package dev.antoniogrillo.esempiocinecaavanzato.model;

import jakarta.persistence.*;
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
    private long id;
    @ManyToOne
    @JoinColumn(name = "casa_automobilistica_id",nullable = false)
    private CasaAutomobilistica marca;
    private String modello;
    @Column(name = "anno_immatricolazione",nullable = false)
    private int annoImmatricolazione;
    private String colore;
    @ManyToMany(mappedBy = "automobili")
    private List<Utente> proprietari;

    @Transient
    private int anni;
}
