package dev.antoniogrillo.esempiocinecaavanzato.service.impl;

import dev.antoniogrillo.esempiocinecaavanzato.repository.CasaAutomobilisticaRepository;
import dev.antoniogrillo.esempiocinecaavanzato.service.def.CasaAutomobilisticaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CasaAutomobilisticaServiceImpl implements CasaAutomobilisticaService {

    private final CasaAutomobilisticaRepository repo;
}
