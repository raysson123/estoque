package br.edu.ifg.luziania.model.dao;

import br.edu.ifg.luziania.model.entity.DadosProduto;
import br.edu.ifg.luziania.model.entity.UsarioRetiraProdutos;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
@ApplicationScoped
public class UsarioRetiraProdutoDAO {
    @Inject
    EntityManager entityManager;
    @Transactional
    public void cadastrarDadosProdutos(List<UsarioRetiraProdutos> listaDadosProduto) {
        for (UsarioRetiraProdutos usarioRetiraProdutos : listaDadosProduto) {
            entityManager.persist(usarioRetiraProdutos);
        }
        // É uma boa prática realizar o flush e o clear após a inserção de entidades em lote
        entityManager.flush();
        entityManager.clear();
    }
}
