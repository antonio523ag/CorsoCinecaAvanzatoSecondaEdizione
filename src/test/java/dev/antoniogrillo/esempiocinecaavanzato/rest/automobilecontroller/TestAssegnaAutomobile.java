package dev.antoniogrillo.esempiocinecaavanzato.rest.automobilecontroller;

import dev.antoniogrillo.esempiocinecaavanzato.EsempioCinecaAvanzatoApplication;
import dev.antoniogrillo.esempiocinecaavanzato.model.Ruolo;
import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;
import dev.antoniogrillo.esempiocinecaavanzato.service.def.GestoreTokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = EsempioCinecaAvanzatoApplication.class)
public class TestAssegnaAutomobile {

    @Autowired
    private MockMvc mock;

    @Autowired
    private GestoreTokenService service;


//    @Test
//    public void assegnaAutomobile() throws Exception {
//        mock.perform(MockMvcRequestBuilders.post("/authenticated/utente/assegnaAuto/{id}",1)
//                .with(SecurityMockMvcRequestPostProcessors.user("m.rossi@email.com")))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }

    @Test
    public void assegnaAutomobile2() throws Exception {
        Utente u=new Utente();
        u.setEmail("m.rossi@email.com");
        String token =service.generaToken(u);
        mock.perform(MockMvcRequestBuilders.post("/authenticated/utente/assegnaAuto/{id}",1)
                        .header("Authorization","Bearer "+ token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
