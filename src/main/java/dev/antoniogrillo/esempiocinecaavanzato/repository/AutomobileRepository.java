package dev.antoniogrillo.esempiocinecaavanzato.repository;

import dev.antoniogrillo.esempiocinecaavanzato.model.Automobile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutomobileRepository extends JpaRepository<Automobile,Long> {

    List<Automobile> findAllByMarca_Id(long id);
    List<Automobile> findAllByProprietari_id(long id);
    List<Automobile> findAllByAnnoImmatricolazioneAfterOrderByAnnoImmatricolazione(int annoMinimo);
}
