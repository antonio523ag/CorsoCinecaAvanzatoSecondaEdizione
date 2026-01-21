package dev.antoniogrillo.esempiocinecaavanzato.service.jpa;

import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;
import dev.antoniogrillo.esempiocinecaavanzato.repository.UtenteRepository;
import dev.antoniogrillo.esempiocinecaavanzato.service.def.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UtenteServiceImpl implements UtenteService {

    private final UtenteRepository repo;

    @Override
    public boolean registraUtente(Utente u) {
        u=repo.save(u);
        return u.getId()!=0;
    }

    @Override
    public List<Utente> getAllUtenti() {
        return repo.findAll();
    }

    @Override
    public Page<Utente> getAllUtenti(int pagina, int dimensionePagina) {
        PageRequest pageRequest=PageRequest.of(pagina,dimensionePagina);
        return repo.findAll(pageRequest);
    }

    @Override
    public Optional<Utente> login(String email,String password) {
        return repo.findByEmailAndPassword(email,password);
    }

    @Override
    public Optional<Utente> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public Optional<Utente> getById(long id) {
        return repo.findById(id);
    }
}
