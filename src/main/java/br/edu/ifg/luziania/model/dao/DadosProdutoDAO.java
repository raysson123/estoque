package br.edu.ifg.luziania.model.dao;

import br.edu.ifg.luziania.model.entity.DadosProduto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class DadosProdutoDAO {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public void cadastrarProduto(DadosProduto dadosProduto) {
        entityManager.persist(dadosProduto);
    }



    public List<DadosProduto> listarTodos() {
        return entityManager.createQuery("SELECT dp FROM DadosProduto dp", DadosProduto.class)
                .getResultList();
    }
    @Transactional
    public void cadastrarDadosProdutos(List<DadosProduto> listaDadosProduto) {
        for (DadosProduto dadosProduto : listaDadosProduto) {
            entityManager.persist(dadosProduto);
        }
        // É uma boa prática realizar o flush e o clear após a inserção de entidades em lote
        entityManager.flush();
        entityManager.clear();
    }

}
