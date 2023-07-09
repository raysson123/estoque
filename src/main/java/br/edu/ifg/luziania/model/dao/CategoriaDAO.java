package br.edu.ifg.luziania.model.dao;

import br.edu.ifg.luziania.model.entity.Categoria;

import br.edu.ifg.luziania.model.entity.Fornecedor;
import br.edu.ifg.luziania.model.entity.Produtos;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;


import java.util.List;

@ApplicationScoped
public class CategoriaDAO {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void cadastrarCategoria(Categoria categoria) {
        entityManager.persist(categoria);
    }


    public List<Object[]> listarTodasCategorias() {
        TypedQuery<Object[]> query = entityManager.createQuery("SELECT c, CASE WHEN EXISTS " +
                "(SELECT 1 FROM Produtos p WHERE p.categoria = c) " +
                "THEN true ELSE false END  FROM Categoria c", Object[].class);
        return query.getResultList();
    }


    @Transactional
    public boolean verificarExistenciaCategoria(String nomeCategoria) {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(c) FROM Categoria c WHERE c.nomeCategoria = :nome", Long.class);
        query.setParameter("nome", nomeCategoria);
        return query.getSingleResult() > 0;
    }

    @Transactional
    public Categoria obterCategoriaPorNome(String nomeCategoria) {
        TypedQuery<Categoria> query = entityManager.createQuery("SELECT c FROM Categoria c WHERE c.nomeCategoria = :nome", Categoria.class);
        query.setParameter("nome", nomeCategoria);
        return query.getSingleResult();
    }

    public Categoria obterCategoriaPorId(Integer idCategoria) {
        TypedQuery<Categoria> query = entityManager.createQuery("SELECT c FROM Categoria c WHERE c.id = :id", Categoria.class);
        query.setParameter("id", idCategoria);
        return query.getSingleResult();
    }


    @Transactional
    public void excluirCategoriaSeNaoPossuirProdutos(Categoria categoria) {
        // Verifica se a categoria possui registros relacionados na tabela Produto
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(p) FROM Produtos p WHERE p.categoria = :categoria",
                Long.class
        );
        query.setParameter("categoria", categoria);
        long count = query.getSingleResult();

        // Se a categoria não possuir registros relacionados, exclui a categoria
        if (count == 0) {
            entityManager.remove(entityManager.contains(categoria) ? categoria : entityManager.merge(categoria));
        }
    }

    @Transactional
    public void atualizarProduto(Categoria categoria) {

        entityManager.merge(categoria);
    }
}

