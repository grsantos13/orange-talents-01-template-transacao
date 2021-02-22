package br.com.zup.transacao.kafka.evento;

import br.com.zup.transacao.transacao.Transacao;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EventoTransacao {
    @NotBlank
    private String id;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull
    private EstabelecimentoEvento estabelecimento;
    @NotNull
    private CartaoEvento cartao;
    @NotNull
    private LocalDateTime efetivadaEm;

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public EstabelecimentoEvento getEstabelecimento() {
        return estabelecimento;
    }

    public CartaoEvento getCartao() {
        return cartao;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    public Transacao toModel(EntityManager manager) {
        return new Transacao(id, valor, cartao.toModel(manager), estabelecimento.toModel(manager), efetivadaEm);
    }
}
