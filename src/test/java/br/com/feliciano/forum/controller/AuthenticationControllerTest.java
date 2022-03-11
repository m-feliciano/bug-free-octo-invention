package br.com.feliciano.forum.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") //force profile
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void authenticateMustReturn4xxClientError() throws Exception {
        URI uri = new URI("/auth");

        String json = "{ \"email\": \"invalis@invalid.com\", \"password\": \"12345\" }";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status()
                        .isBadRequest());
    }

    @Test
    void authenticateMustReturn2xxSuccessful() throws Exception {
        URI uri = new URI("/auth");

        String json = "{ \"email\": \"ana@email.com\", \"password\": \"12345\" }";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status()
                        .is2xxSuccessful());
    }
}