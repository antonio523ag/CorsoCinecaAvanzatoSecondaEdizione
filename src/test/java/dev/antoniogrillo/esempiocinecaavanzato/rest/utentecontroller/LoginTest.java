package dev.antoniogrillo.esempiocinecaavanzato.rest.utentecontroller;

import dev.antoniogrillo.esempiocinecaavanzato.EsempioCinecaAvanzatoApplication;
import dev.antoniogrillo.esempiocinecaavanzato.dto.request.LoginDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@ContextConfiguration(classes = EsempioCinecaAvanzatoApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
public class LoginTest {

    @Autowired
    private MockMvc mock;
    @Autowired
    private ObjectMapper mapper;

    @Test
    @Order(20)
    public void testLoginConDatiGiusti() throws Exception {
        String json=getLoginRequestJson("m.rossi@email.com","1234");
        RequestBuilder rb= MockMvcRequestBuilders.post("/all/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json);
        ResultMatcher r1= MockMvcResultMatchers.status().isOk();
        ResultMatcher r2= MockMvcResultMatchers.jsonPath("$.nome").value("mario");
        mock.perform(rb).andExpect(r1).andExpect(r2);
    }

    @Test
    @Order(1)
    public void testLoginConDatiErrati() throws Exception {
        String json=getLoginRequestJson("f.botti@email.com","1234");
        RequestBuilder rb= MockMvcRequestBuilders.post("/all/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json);
        ResultMatcher r1= MockMvcResultMatchers.status().is4xxClientError();
        mock.perform(rb).andExpect(r1);
    }

    @Test
    @Order(2)
    public void testLoginSenzaPassword() throws Exception {
        String json=getLoginRequestJson("f.botti@email.com",null);
        RequestBuilder rb= MockMvcRequestBuilders.post("/all/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json);
        ResultMatcher r1= MockMvcResultMatchers.status().is4xxClientError();
        mock.perform(rb).andExpect(r1);
    }

    @Test
    @Order(2)
    public void testLoginSenzaEmail() throws Exception {
        String json=getLoginRequestJson(null,"1234");
        RequestBuilder rb= MockMvcRequestBuilders.post("/all/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json);
        ResultMatcher r1= MockMvcResultMatchers.status().is4xxClientError();
        mock.perform(rb).andExpect(r1);
    }

    @Test
    public void testLoginEmailNonFormattataCorrettamente() throws Exception {
        String json=getLoginRequestJson("f.botti","1234");
        RequestBuilder rb= MockMvcRequestBuilders.post("/all/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json);
        ResultMatcher r1= MockMvcResultMatchers.status().is4xxClientError();
        mock.perform(rb).andExpect(r1);
    }

    @Test
    public void testLoginPasswordTroppoCorta() throws Exception {
        String json=getLoginRequestJson("f.botti@email.com","123");
        RequestBuilder rb= MockMvcRequestBuilders.post("/all/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json);
        ResultMatcher r1= MockMvcResultMatchers.status().is4xxClientError();
        mock.perform(rb).andExpect(r1);
    }



    public String getLoginRequestJson(String email,String password){
        LoginDTO loginDTO=new LoginDTO(email,password);
        return mapper.writeValueAsString(loginDTO);
    }


}
