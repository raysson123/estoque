package br.edu.ifg.luziania.model.dao;

import br.edu.ifg.luziania.model.dto.FornecedorDTO;
import br.edu.ifg.luziania.model.entity.Categoria;
import br.edu.ifg.luziania.model.entity.DadosProduto;
import br.edu.ifg.luziania.model.entity.Fornecedor;
import br.edu.ifg.luziania.model.entity.Produtos;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class FornecedorDAO {
    @Inject
    EntityManager entityManager;

    @Transactional
    public void cadastrarFornecedor(Fornecedor fornecedor) {
        entityManager.persist(fornecedor);
    }
    @Transactional
    public Fornecedor findById(Integer id) {
        return entityManager.find(Fornecedor.class, id);
    }
    public List<Object[]> listarTodos() {
        TypedQuery<Object[]> query = entityManager.createQuery(
                "SELECT c, CASE WHEN EXISTS " +
                        "(SELECT 1 FROM DadosProduto p WHERE p.fornecedor = c) " +
                        "THEN true ELSE false END " +
                        "FROM Fornecedor c",
                Object[].class
        );
        return query.getResultList();
    }

    @Transactional
    public void excluirFonecedorSeNaoPossuirProdutos(Fornecedor fornecedor) {
        // Verifica se a categoria possui registros relacionados na tabela Produto
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(p) FROM DadosProduto p WHERE p.fornecedor = :fornecedor",
                Long.class
        );
        query.setParameter("fornecedor", fornecedor);
        long count = query.getSingleResult();

        // Se a categoria não possuir registros relacionados, exclui a categoria
        if (count == 0) {
            entityManager.remove(entityManager.contains(fornecedor) ? fornecedor : entityManager.merge(fornecedor));
        }
    }
    @Transactional
    public void atualizarProduto(Fornecedor fornecedor) {
        entityManager.merge(fornecedor);
    }






}
