package br.edu.ifg.luziania.model.bo;

import br.edu.ifg.luziania.model.dao.FornecedorDAO;
import br.edu.ifg.luziania.model.dto.FornecedorDTO;
import br.edu.ifg.luziania.model.dto.ProdutosDTO;
import br.edu.ifg.luziania.model.dto.RespostaDTO;
import br.edu.ifg.luziania.model.entity.Fornecedor;
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
public class FornecedorBO {

    @Inject
    FornecedorDAO fornecedorDAO;

    public Response cadastrarFornecedor(FornecedorDTO fornecedorDTO) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNomeFornecedor(fornecedorDTO.getNomeFornecedor());
        fornecedor.setNomeDeContato(fornecedorDTO.getNomeDeContato());
        fornecedor.setNumeroDeTelefone(fornecedorDTO.getNumeroDeTelefone());
        fornecedor.setNumeroCNPJ(fornecedorDTO.getNumeroCNPJ());
        fornecedor.setNumeroEmail(fornecedorDTO.getNumeroEmail());

        try {
            fornecedorDAO.cadastrarFornecedor(fornecedor);
            return Response.ok(new RespostaDTO(200, "Fornecedor cadastrado com sucesso!", "/")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new RespostaDTO(500, "Falha ao cadastrar fornecedor!", "/"))
                    .build();
        }
    }

    public Response listarFornecedores() {
        List<Object[]> fornecedoresComEstoque = fornecedorDAO.listarTodos();
        List<FornecedorDTO> fornecedorDTOS = new ArrayList<>();

        for (Object[] fornecedor : fornecedoresComEstoque) {

            Fornecedor f = (Fornecedor)fornecedor[0];
          boolean Posueprudos= (Boolean) fornecedor[1];
            // Acessar os campos do fornecedor e adicionar os valores ao DTO
            FornecedorDTO fornecedorDTO = new FornecedorDTO();
            fornecedorDTO.setId(f.getId());
            fornecedorDTO.setNomeFornecedor(f.getNomeFornecedor());
            fornecedorDTO.setNomeDeContato(f.getNomeDeContato());
            fornecedorDTO.setNumeroDeTelefone(f.getNumeroDeTelefone());
            fornecedorDTO.setNumeroCNPJ(f.getNumeroCNPJ());
            fornecedorDTO.setNumeroEmail(f.getNumeroEmail());
            fornecedorDTO.setPosueprudos(Posueprudos);

            fornecedorDTOS.add(fornecedorDTO);
        }

        try {
            // Converter objeto em JSON usando JSON-B
            Jsonb jsonb = JsonbBuilder.create();
            String json = jsonb.toJson(fornecedorDTOS);

            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new RespostaDTO(500, "Falha ao listar fornecedores!", "/"))
                    .build();
        }
    }

    @Transactional
    public Response DeletaraFornecedor(Integer id) {
        // Buscar o produto pelo ID
        Fornecedor fornecedor = fornecedorDAO.findById(id);

        // Verificar se o produto existe
        if (fornecedor == null) {
            // Caso não exista, retornar uma resposta de não encontrado
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {
            // Excluir o produto se não possuir dados associados
            fornecedorDAO.excluirFonecedorSeNaoPossuirProdutos(fornecedor);

            // Retornar uma resposta de sucesso sem conteúdo
            return Response.ok(new RespostaDTO(200, "Cadastro excluir com sucesso!", "/")).build();
        } catch (Exception e) {
            // Em caso de falha, retornar uma resposta de erro interno do servidor
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new RespostaDTO(500, "Falha ao excluir Cadastro!", "/"))
                    .build();
        }
    }
    @Transactional
    public Response atualizarFornecedor(Integer id, FornecedorDTO fornecedorDTO) {
        // Buscar o produto existente pelo ID
        Fornecedor FornecedorExistente = fornecedorDAO.findById(id);

        // Verificar se o produto existe
        if (FornecedorExistente == null) {
            // Caso não exista, retornar uma resposta de não encontrado
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // Criar uma nova instância do objeto Produtos
        Fornecedor fornecedor = new Fornecedor();

        // Definir os valores dos atributos com base nos dados do DTO
        fornecedor.setNomeFornecedor(fornecedorDTO.getNomeFornecedor());
        fornecedor.setNomeDeContato(fornecedorDTO.getNomeDeContato());
        fornecedor.setNumeroDeTelefone(fornecedorDTO.getNumeroDeTelefone());
        fornecedor.setNumeroCNPJ(fornecedorDTO.getNumeroCNPJ());
        fornecedor.setNumeroEmail(fornecedorDTO.getNumeroEmail());




        fornecedor.setId(id);

        try {
            // Atualizar o produto
            fornecedorDAO.atualizarProduto(fornecedor);

            // Retornar uma resposta de sucesso
            return Response.ok(new RespostaDTO(200, "Produto atualizado com sucesso!", "/")).build();
        } catch (Exception e) {
            // Em caso de falha, retornar uma resposta de erro interno do servidor
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new RespostaDTO(500, "Falha ao atualizar produto!", "/"))
                    .build();
        }
    }


}