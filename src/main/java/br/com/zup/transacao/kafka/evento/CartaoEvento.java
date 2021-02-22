package br.com.zup.transacao.kafka.evento;

import br.com.zup.transacao.transacao.cartao.Cartao;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CartaoEvento {
    private String id;
    private String email;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Cartao toModel(EntityManager manager) {
        TypedQuery<Cartao> query = manager.createQuery(" select c from Cartao c where numero = :numero", Cartao.class);
        query.setParameter("numero", id);
        List<Cartao> resultList = query.getResultList();

        Assert.state(resultList.size() <= 1, "Mais de um cartão igual foi encontrado.");

        if (resultList.isEmpty()) return new Cartao(id, email);
        else return resultList.get(0);
    }
}
