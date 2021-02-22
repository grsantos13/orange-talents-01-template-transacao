package br.com.zup.transacao.transacao;

import br.com.zup.transacao.transacao.cartao.Cartao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import java.util.UUID;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private TransacaoRepository repository;
    private EntityManager manager;

    public TransacaoController(TransacaoRepository repository, EntityManager manager) {
        this.repository = repository;
        this.manager = manager;
    }

    @GetMapping("/cartao/{id}")
    public ResponseEntity<Page<TransacaoResponse>> buscarUltimasTransacoes(@PathVariable("id") UUID id,
                                                                   @PageableDefault(size = 10, sort = "efetivadaEm", direction = DESC) Pageable pageable) {
        Cartao cartao = manager.find(Cartao.class, id);
        if (cartao == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado para o id " + id);


        Page<TransacaoResponse> transacoes = repository.findByCartaoId(id, pageable)
                .map(TransacaoResponse::new);

        return  ResponseEntity.ok(transacoes);
    }
}
