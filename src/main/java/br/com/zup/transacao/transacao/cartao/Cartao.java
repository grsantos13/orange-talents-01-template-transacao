package br.com.zup.transacao.transacao.cartao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    @Column(nullable = false)
    private String numero;

    @Email
    @NotBlank
    @Column(nullable = false)
    private String email;

    @Deprecated
    public Cartao() {
    }

    public Cartao(@NotBlank String numero, String email) {
        this.numero = numero;
        this.email = email;
    }

    public String getNumero() {
        return numero;
    }

    public String getEmail() {
        return email;
    }
}
