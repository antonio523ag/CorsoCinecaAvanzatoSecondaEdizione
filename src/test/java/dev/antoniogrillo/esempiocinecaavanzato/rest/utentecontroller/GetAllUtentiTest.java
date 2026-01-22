package dev.antoniogrillo.esempiocinecaavanzato.rest.utentecontroller;

import dev.antoniogrillo.esempiocinecaavanzato.EsempioCinecaAvanzatoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ContextConfiguration(classes = EsempioCinecaAvanzatoApplication.class)
@AutoConfigureMockMvc
public class GetAllUtentiTest {

    @Autowired
    private MockMvc mock;

    @Test
    public void testGetAllUtenti() throws Exception {
        RequestBuilder rb= MockMvcRequestBuilders.get("/all/utenti");
        ResultActions result = mock.perform(rb);
        ResultMatcher rm1= MockMvcResultMatchers.status().is4xxClientError();
        ResultMatcher rm2=MockMvcResultMatchers.jsonPath("$").isArray();
        ResultMatcher rm3=MockMvcResultMatchers.jsonPath("$[0].nome").isString();
        ResultMatcher rm4=MockMvcResultMatchers.jsonPath("$[0].nome").value("mario");
        result.andExpect(rm1).andExpect(rm2).andExpect(rm3).andExpect(rm4);
        result.andDo(MockMvcResultHandlers.print());


    }
}
