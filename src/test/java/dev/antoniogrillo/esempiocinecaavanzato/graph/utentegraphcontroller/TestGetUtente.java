package dev.antoniogrillo.esempiocinecaavanzato.graph.utentegraphcontroller;

import dev.antoniogrillo.esempiocinecaavanzato.model.Utente;
import dev.antoniogrillo.esempiocinecaavanzato.service.def.GestoreTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestGetUtente {

    @LocalServerPort
    private int porta;
    private GraphQlTester testerSenzaAutenticazione,testerConAutenticazione;

    @Autowired
    private GestoreTokenService service;

    @BeforeEach
    public void inizializza(){
        WebTestClient.Builder builder = WebTestClient.bindToServer().baseUrl("http://localhost:"+porta+"/graphql");
        testerSenzaAutenticazione = HttpGraphQlTester.builder(builder).build();
        Utente u=new Utente();
        u.setEmail("m.rossi@email.com");
        String token=service.generaToken(u);
        builder = WebTestClient.bindToServer().baseUrl("http://localhost:"+porta+"/authenticated/graphql")
                .defaultHeader("Authorization","Bearer "+token);
        testerConAutenticazione = HttpGraphQlTester.builder(builder).build();
    }

    @Test
    void testLogin(){
        String body= """
                query Login {
                    login(input: { username: "m.rossi@email.com", password: "1234" }) {
                        token
                        utente {
                            username
                        }
                    }
                }
                
                """;
        testerSenzaAutenticazione
                .document(body)
                .execute()
                .path("login.token")
                .entity(String.class)
                .path("login.utente.username")
                .entity(String.class)
                .isEqualTo("m.rossi@email.com");
    }
}
