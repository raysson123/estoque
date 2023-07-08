package br.edu.ifg.luziania.model.dao;

import br.edu.ifg.luziania.model.dto.ProdutosDTO;
import br.edu.ifg.luziania.model.entity.DadosProduto;
import br.edu.ifg.luziania.model.entity.Produtos;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;

import java.util.List;
@ApplicationScoped
public class ProdutosDAO {

    @Inject
    private EntityManager entityManager;
    @Inject
    Jsonb jsonb;
    public List<Produtos> listarTodosProdutos() {
        TypedQuery<Produtos> query = entityManager.createQuery("SELECT p FROM Produtos p", Produtos.class);
        return query.getResultList();
    }
    public String obterProdutosComEstoque() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProdutosDTO> cq = cb.createQuery(ProdutosDTO.class);
        Root<Produtos> root = cq.from(Produtos.class);

        // Junção das tabelas
        Join<Produtos, DadosProduto> joinDadosProduto = root.join("dadosProduto", JoinType.LEFT);

        // Expressão para calcular a soma da coluna "quantidadeDisponivel"
        Expression<Integer> somaQuantidades = cb.sum(joinDadosProduto.get("quantidadeDisponivel"));

        // Função CASE para verificar se a soma é maior que 0
        Expression<Object> possuiEstoque = cb.selectCase()
                .when(cb.greaterThan(somaQuantidades, 0), true)
                .otherwise(false);

        // Seleção dos campos da tabela "Produtos" e do resultado da verificação
        cq.multiselect(
                root.get("id"),
                root.get("nomeProdutos"),
                root.get("descricao"),
                root.get("categoria").get("nomeCategoria"),
                possuiEstoque
        );

        // Agrupamento por ID do produto
        cq.groupBy(root.get("id"));

        TypedQuery<ProdutosDTO> query = entityManager.createQuery(cq);
        List<ProdutosDTO> produtos = query.getResultList();

        return jsonb.toJson(produtos);
    }

    @Transactional
    public void cadastrarProduto(Produtos produto) {
        entityManager.persist(produto);
    }
    @Transactional
    public void atualizarProduto(Produtos produto) {
        entityManager.merge(produto);
    }

    @Transactional
    public void excluirProdutoSeNaoPossuirDadosProduto(Produtos produto) {
        // Verifica se o produto possui registros relacionados na tabela DadosProduto
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(d) FROM DadosProduto d WHERE d.produtos = :produto",
                Long.class
        );
        query.setParameter("produto", produto);
        long count = query.getSingleResult();

        // Se o produto não possuir registros relacionados, exclui o produto
        if (count == 0) {
            entityManager.remove(entityManager.contains(produto) ? produto : entityManager.merge(produto));
        }
    }

    @Transactional
    public Produtos buscarProdutoPorId(Integer id) {
        return entityManager.find(Produtos.class, id);
    }
}