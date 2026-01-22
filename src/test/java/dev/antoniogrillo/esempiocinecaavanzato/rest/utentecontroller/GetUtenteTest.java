package dev.antoniogrillo.esempiocinecaavanzato.rest.utentecontroller;

import dev.antoniogrillo.esempiocinecaavanzato.EsempioCinecaAvanzatoApplication;
import dev.antoniogrillo.esempiocinecaavanzato.dto.request.LoginDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@ContextConfiguration(classes = {EsempioCinecaAvanzatoApplication.class})
@AutoConfigureMockMvc
public class GetUtenteTest {

    @Autowired
    private MockMvc mock;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testGetUtente()throws Exception{
        String token=getToken();
        RequestBuilder rb= MockMvcRequestBuilders.get("/all/utente/autenticato")
                .header("Authorization","Bearer "+ token);
        mock.perform(rb).andExpect(MockMvcResultMatchers.status().isOk());

    }

    private String getToken() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.post("/all/login")
                .contentType("application/json")
                .content(mapper.writeValueAsString(new LoginDTO("m.rossi@email.com", "1234")));
        return mock.perform(rb).andReturn().getResponse().getHeader("Authorization");
    }

}
