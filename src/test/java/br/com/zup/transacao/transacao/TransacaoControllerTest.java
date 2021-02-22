package br.com.zup.transacao.transacao;

import br.com.zup.transacao.transacao.cartao.Cartao;
import br.com.zup.transacao.transacao.estabelecimento.Estabelecimento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class TransacaoControllerTest {

    @Autowired
    private TransacaoRepository repository;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private TransacaoController controller;
    @MockBean
    private EntityManager manager;

    private Transacao transacao;

    @BeforeEach
    void setup() {
        transacao = new Transacao("1",
                BigDecimal.ONE,
                new Cartao("1111-1111-1111-1111", "g@email.com"),
                new Estabelecimento("n", "c", "e"),
                LocalDateTime.now());
        repository.save(transacao);
    }

    @Test
    @DisplayName("Deve retornar ok quando houver o cartão.")
    void teste1() throws Exception {
        when(manager.find(any(), any(UUID.class))).thenReturn(transacao.getCartao());
        mvc.perform(get("/transacoes/cartao/{id}", UUID.randomUUID()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve dar erro ao não encontrar o cartão.")
    void teste2() throws Exception {
        when(manager.find(any(), any(UUID.class))).thenReturn(null);
        mvc.perform(get("/transacoes/cartao/{id}", UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }


}