package dev.antoniogrillo.esempiocinecaavanzato.repository;

import dev.antoniogrillo.esempiocinecaavanzato.model.Automobile;
import dev.antoniogrillo.esempiocinecaavanzato.model.CasaAutomobilistica;
import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AutomobiliCriteraRepository {

    private final EntityManager manager;


//    public Automobile getAutomobiliById(long id) {
//        CriteriaBuilder builder=manager.getCriteriaBuilder();
//        CriteriaQuery<Automobile> query=builder.createQuery(Automobile.class);
//        Root<Automobile> root=query.from(Automobile.class);
//        Predicate p=builder.equal(root.get("id"),id);
//        query.where(p);
//        try {
//            return manager.createQuery(query).getSingleResult();
//        }catch (NoResultException e){return null;}
//
//    }
//
//    public List<Automobile> getAutomobiliByUtente(Utente u) {
//        CriteriaBuilder builder = manager.getCriteriaBuilder();
//        CriteriaQuery<Automobile> query = builder.createQuery(Automobile.class);
//        Root<Automobile> root = query.from(Automobile.class);
//        Join<Automobile,Utente> utenti=root.join("proprietari");
//        Predicate p=builder.equal(utenti.get("id"),u.getId());
//        query.where(p);
//        return manager.createQuery(query).getResultList();
//    }
//
    public List<Automobile> getAutomobiliByAnnoMinimo(int annoMinimo) {
        CriteriaBuilder builder=manager.getCriteriaBuilder();
        CriteriaQuery<Automobile> query=builder.createQuery(Automobile.class);
        Root<Automobile> root=query.from(Automobile.class);
        Predicate p=builder.greaterThanOrEqualTo(root.get("anno"),annoMinimo);
        query.where(p);
        return manager.createQuery(query).getResultList();
    }
//
//    public List<Automobile> getAutomobiliByMarca(long id) {
//        CriteriaBuilder builder=manager.getCriteriaBuilder();
//        CriteriaQuery<Automobile> query=builder.createQuery(Automobile.class);
//        Root<Automobile> root=query.from(Automobile.class);
//        Join<Automobile, CasaAutomobilistica> marca=root.join("marca");
//        Predicate p=builder.equal(marca.get("id"),id);
//        query.where(p);
//        return manager.createQuery(query).getResultList();
//    }


    public Automobile getAutomobiliById(long id) {
        return getAll(List.of(new Param<>("automobile","id",id))).get(0);
    }
    public List<Automobile> getAutomobiliByUtente(Utente u) {
        return getAll(List.of(new Param<>("utente","id",u.getId())));
    }
    public List<Automobile> getAutomobiliByMarca(long id) {
        return getAll(List.of(new Param<>("marca","id",id)));
    }


    private<P> List<Automobile> getAll(List<Param<P>> params){
        CriteriaBuilder builder=manager.getCriteriaBuilder();
        CriteriaQuery<Automobile> query=builder.createQuery(Automobile.class);
        Root<Automobile> root=query.from(Automobile.class);
        Join<Automobile,CasaAutomobilistica> marca=root.join("marca");
        Join<Automobile,Utente> utenti=root.join("proprietari");
        List<Predicate> listaCondizioni=new ArrayList<>();
        for(Param p:params){
            From f=switch (p.nomeClasse){
                case "automobile" -> root;
                case "marca" -> marca;
                case "utente" -> utenti;
                default -> null;
            };
            Predicate condizione=builder.equal(f.get(p.nomeCampo),p.valore);
            listaCondizioni.add(condizione);
        }
        query.where(listaCondizioni.toArray(Predicate[]::new));
        return manager.createQuery(query).getResultList();
    }

    private record Param<P>(String nomeClasse,String nomeCampo,P valore){}
}
