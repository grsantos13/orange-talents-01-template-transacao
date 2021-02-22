package br.com.zup.transacao.transacao;

import br.com.zup.transacao.transacao.cartao.Cartao;
import br.com.zup.transacao.transacao.estabelecimento.Estabelecimento;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transacao {
    @Id
    private String id;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    private Cartao cartao;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    private Estabelecimento estabelecimento;

    @NotNull
    private LocalDateTime efetivadaEm;

    @Deprecated
    public Transacao() {
    }

    public Transacao(String id,
                     @NotNull @Positive BigDecimal valor,
                     @NotNull Cartao cartao,
                     @NotNull Estabelecimento estabelecimento,
                     @NotNull LocalDateTime efetivadaEm) {
        this.id = id;
        this.valor = valor;
        this.cartao = cartao;
        this.estabelecimento = estabelecimento;
        this.efetivadaEm = efetivadaEm;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }
}
