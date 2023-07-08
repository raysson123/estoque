package br.edu.ifg.luziania.model.bo;

import br.edu.ifg.luziania.model.dao.CategoriaDAO;


import br.edu.ifg.luziania.model.dao.DadosProdutoDAO;
import br.edu.ifg.luziania.model.dao.FornecedorDAO;
import br.edu.ifg.luziania.model.dao.ProdutosDAO;
import br.edu.ifg.luziania.model.dto.DadosProdutoDTO;
import br.edu.ifg.luziania.model.dto.ProdutosDTO;
import br.edu.ifg.luziania.model.dto.RespostaDTO;
import br.edu.ifg.luziania.model.entity.DadosProduto;
import br.edu.ifg.luziania.model.entity.Produtos;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Dependent
public class ProdutosBO {
    @Inject
    ProdutosDAO produtosDAO;
    @Inject
    CategoriaDAO categoriaDAO;


    @Transactional
    public Response cadastrarProduto(ProdutosDTO produtosDTO) {
        // Criar uma nova instância do objeto Produtos
        Produtos produtos = new Produtos();

        // Definir os valores dos atributos com base nos dados do DTO
        produtos.setNomeProdutos(produtosDTO.getNomeProdutos());
        produtos.setDescricao(produtosDTO.getDescricao());

        // Verificar se a categoria existe
        if (categoriaDAO.verificarExistenciaCategoria(produtosDTO.getNomeCategoria())) {
            // Obter a categoria pelo nome
            produtos.setCategoria(categoriaDAO.obterCategoriaPorNome(produtosDTO.getNomeCategoria()));
        }

        try {
            // Cadastrar o produto
            produtosDAO.cadastrarProduto(produtos);

            // Retornar uma resposta de sucesso
            return Response.ok(new RespostaDTO(200, "Produto cadastrado com sucesso!", "/")).build();
        } catch (Exception e) {
            // Em caso de falha, retornar uma resposta de erro interno do servidor
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new RespostaDTO(500, "Falha ao cadastrar produto!", "/"))
                    .build();
        }
    }

    @Transactional
    public Response atualizarProduto(Integer id, ProdutosDTO produtosDTO) {
        // Buscar o produto existente pelo ID
        Produtos produtoExistente = produtosDAO.buscarProdutoPorId(id);

        // Verificar se o produto existe
        if (produtoExistente == null) {
            // Caso não exista, retornar uma resposta de não encontrado
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // Criar uma nova instância do objeto Produtos
        Produtos produtos = new Produtos();

        // Definir os valores dos atributos com base nos dados do DTO
        produtos.setNomeProdutos(produtosDTO.getNomeProdutos());
        produtos.setDescricao(produtosDTO.getDescricao());

        // Verificar se a categoria existe
        if (categoriaDAO.verificarExistenciaCategoria(produtosDTO.getNomeCategoria())) {
            // Obter a categoria pelo nome
            produtos.setCategoria(categoriaDAO.obterCategoriaPorNome(produtosDTO.getNomeCategoria()));
        }

        produtos.setId(id);

        try {
            // Atualizar o produto
            produtosDAO.atualizarProduto(produtos);

            // Retornar uma resposta de sucesso
            return Response.ok(new RespostaDTO(200, "Produto atualizado com sucesso!", "/")).build();
        } catch (Exception e) {
            // Em caso de falha, retornar uma resposta de erro interno do servidor
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new RespostaDTO(500, "Falha ao atualizar produto!", "/"))
                    .build();
        }
    }

    @Transactional
    public Response deletarProduto(Integer id) {
        // Buscar o produto pelo ID
        Produtos produto = produtosDAO.buscarProdutoPorId(id);

        // Verificar se o produto existe
        if (produto == null) {
            // Caso não exista, retornar uma resposta de não encontrado
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {
            // Excluir o produto se não possuir dados associados
            produtosDAO.excluirProdutoSeNaoPossuirDadosProduto(produto);

            // Retornar uma resposta de sucesso sem conteúdo
            return Response.ok(new RespostaDTO(200, "Produto excluir com sucesso!", "/")).build();
        } catch (Exception e) {
            // Em caso de falha, retornar uma resposta de erro interno do servidor
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new RespostaDTO(500, "Falha ao excluir produto!", "/"))
                    .build();
        }
    }





}
