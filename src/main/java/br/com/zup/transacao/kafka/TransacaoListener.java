package br.com.zup.transacao.kafka;

import br.com.zup.transacao.kafka.evento.EventoTransacao;
import br.com.zup.transacao.transacao.Transacao;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
public class TransacaoListener {

    private EntityManager manager;

    public TransacaoListener(EntityManager manager) {
        this.manager = manager;
    }


    @KafkaListener(topics = "${kafka.topic.transacoes}")
    @Transactional
    public void ouvir(EventoTransacao eventoTransacao){
        Transacao transacao = eventoTransacao.toModel(manager);
        manager.persist(transacao);
    }
}
