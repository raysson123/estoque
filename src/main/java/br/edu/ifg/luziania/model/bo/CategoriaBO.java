package br.edu.ifg.luziania.model.bo;

import br.edu.ifg.luziania.model.dao.CategoriaDAO;
import br.edu.ifg.luziania.model.dto.CatergoriaDTO;
import br.edu.ifg.luziania.model.dto.RespostaDTO;
import br.edu.ifg.luziania.model.entity.Categoria;

import br.edu.ifg.luziania.model.entity.Produtos;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Dependent
public class CategoriaBO {
    @Inject
    private CategoriaDAO categoriaDAO;

    public Response lista() {
        List<Object[]> categorias = categoriaDAO.listarTodasCategorias();
        List<CatergoriaDTO> catergoriaDTOS = new ArrayList<>();
        if (categorias != null) {
            for (Object[] categoria : categorias) {
                Categoria c = (Categoria) categoria[0];
                boolean possuiProdutos = (boolean) categoria[1];

                CatergoriaDTO categoriaDTO = new CatergoriaDTO();
                categoriaDTO.setNomeCategoria(c.getNomeCategoria());
                categoriaDTO.setId(c.getId());
                categoriaDTO.setPossuiProduto(possuiProdutos);

                catergoriaDTOS.add(categoriaDTO);
            }
        }

        try {
            // Converter objeto em JSON usando JSON-B
            Jsonb jsonb = JsonbBuilder.create();
            String json = jsonb.toJson(catergoriaDTOS);

            return Response.ok(json, MediaType.APPLICATION_JSON).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new RespostaDTO(500, "Falha ao listar categorias!", "/"))
                    .build();
        }
    }
    @Transactional
    public Response cadastrarCategoria(CatergoriaDTO catergoriaDTO) {

        Categoria categoria = new Categoria();
        categoria.setNomeCategoria(catergoriaDTO.getNomeCategoria());
        System.out.println(categoria.getNomeCategoria());
        if (catergoriaDTO.getNomeCategoria() == null) {
            // Caso não exista, retornar uma resposta de não encontrado
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        try {
            categoriaDAO.cadastrarCategoria(categoria);

            return Response.ok(new RespostaDTO(200, "Produto cadastrado com sucesso!", "/")).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new RespostaDTO(500, "Falha ao listar categorias!", "/"))
                    .build();
        }
    }

    @Transactional
    public Response DeletaraCategoria(Integer id) {
        // Buscar o produto pelo ID
        Categoria categoria = categoriaDAO.obterCategoriaPorId(id);

        // Verificar se o produto existe
        if (categoria == null) {
            // Caso não exista, retornar uma resposta de não encontrado
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {
            // Excluir o produto se não possuir dados associados
            categoriaDAO.excluirCategoriaSeNaoPossuirProdutos(categoria);

            // Retornar uma resposta de sucesso sem conteúdo
            return Response.ok(new RespostaDTO(200, "Cadastro excluir com sucesso!", "/")).build();
        } catch (Exception e) {
            // Em caso de falha, retornar uma resposta de erro interno do servidor
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new RespostaDTO(500, "Falha ao excluir Cadastro!", "/"))
                    .build();
        }
    }

}



