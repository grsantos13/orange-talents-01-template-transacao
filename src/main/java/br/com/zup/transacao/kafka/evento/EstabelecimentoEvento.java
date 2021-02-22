package br.com.zup.transacao.kafka.evento;

import br.com.zup.transacao.transacao.estabelecimento.Estabelecimento;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EstabelecimentoEvento {
    private String nome;
    private String cidade;
    private String endereco;

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public Estabelecimento toModel(EntityManager manager) {
        TypedQuery<Estabelecimento> query = manager.createQuery(" select e from Estabelecimento e " +
                " where nome = :nome " +
                " and cidade = :cidade " +
                " and endereco = :endereco ", Estabelecimento.class);
        query.setParameter("nome", nome).setParameter("cidade", cidade).setParameter("endereco", endereco);
        List<Estabelecimento> resultList = query.getResultList();
        if (resultList.isEmpty())
            return new Estabelecimento(nome, cidade, endereco);
        else return resultList.get(0);
    }
}