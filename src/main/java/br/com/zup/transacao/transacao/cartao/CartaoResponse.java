package br.com.zup.transacao.transacao.cartao;

public class CartaoResponse {

    private String numero;
    private String email;

    public CartaoResponse(Cartao cartao) {
        this.numero = cartao.getNumero();
        this.email = cartao.getEmail();
    }

    public String getNumero() {
        return "..." + numero.substring(numero.length() - 6);
    }

    public String getEmail() {
        return email;
    }
}
