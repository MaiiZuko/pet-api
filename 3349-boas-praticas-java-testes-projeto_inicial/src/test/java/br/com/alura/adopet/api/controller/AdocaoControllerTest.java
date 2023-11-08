package br.com.alura.adopet.api.controller;

import org.apache.tomcat.util.http.parser.MediaType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

import br.com.alura.adopet.api.service.AdocaoService;

@SpringBootTest
@AutoConfigureMockMvc //Para injetar o MockMvc
public class AdocaoControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private AdocaoService service;
/* 
    @Test
    void deveriaDevolver400ParaSolicitacaoDeAdcaoComErros() throws Exception {
        //ARRANGE
        java.lang.String json = """

        """;
        //ACT
        //como instanciar um controller
        //Simular uma requisição MockMvc - perform performar uma requisição
        var response = mvc.perform(
                post("/adocoes")
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(400, response.getStatus());
    }
    @Test
    void deveriaDevolver200ParaSolicitacaoDeAdcaoSemErros() throws Exception {
        //ARRANGE
        java.lang.String json = """
            {
                "idPet": 1,
                "idTutor": 1,
                "motivo": "Motivo qualquer"
            }
        """;
        //ACT
        //como instanciar um controller
        //Simular uma requisição MockMvc - perform performar uma requisição
        var response = mvc.perform(
                post("/adocoes")
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(200, response.getStatus());
    }
    */
}
