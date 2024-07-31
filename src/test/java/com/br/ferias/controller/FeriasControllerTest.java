package com.br.ferias.controller;

import com.br.ferias.controller.dto.AprovaFeriasDTO;
import com.br.ferias.controller.dto.FeriasDTO;
import com.br.ferias.controller.dto.SolicitarFeriasDTO;
import com.br.ferias.domain.Ferias;
import com.br.ferias.domain.Funcionario;
import com.br.ferias.service.FeriasService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FeriasController.class)
public class FeriasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeriasService feriasService;

    @Autowired
    private ObjectMapper objectMapper;

    private FeriasDTO feriasDTO;

    @BeforeEach
    public void setUp() {
        // Inicialização dos objetos de teste
        feriasDTO = new FeriasDTO(1L, LocalDate.now(), LocalDate.now().plusDays(1), true, 1L); // Configure com valores de teste conforme necessário
    }

    @Test
    public void testSolicitarFerias() throws Exception {

        SolicitarFeriasDTO solicitarFeriasDTO = new SolicitarFeriasDTO(1L, LocalDate.now(), LocalDate.now().plusDays(1)); // Configure com valores de teste
        when(feriasService.solicitarFerias(any(SolicitarFeriasDTO.class))).thenReturn(Ferias.criarFerias(new Funcionario(), LocalDate.now(), LocalDate.now().plusDays(1))); // Configure retorno esperado


        mockMvc.perform(post("/ferias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(solicitarFeriasDTO)))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void testAprovarFerias() throws Exception {
        // Dado
        AprovaFeriasDTO aprovaFeriasDTO = new AprovaFeriasDTO(1L, true); // Configure com valores de teste
        when(feriasService.processarSolicitacaoDeFerias(any(AprovaFeriasDTO.class))).thenReturn(new Ferias()); // Configure retorno esperado

        // Quando
        mockMvc.perform(post("/ferias/aprovar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(aprovaFeriasDTO)))
                .andExpect(status().isOk());
    }
}