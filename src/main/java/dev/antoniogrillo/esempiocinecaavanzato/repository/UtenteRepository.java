package dev.antoniogrillo.esempiocinecaavanzato.repository;

import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente,Long> {

    @Query(nativeQuery = true,value = "select * from utente where username = :email and password = :password")
    Optional<Utente> loginSQL(String email, String password);

    @Query("select u from Utente u where u.email = :email and u.password = :password")
    Optional<Utente> loginJPQL(String email, String password);

    Optional<Utente> findByEmailAndPassword(String email, String password);

    Optional<Utente> findByEmail(String email);
}
