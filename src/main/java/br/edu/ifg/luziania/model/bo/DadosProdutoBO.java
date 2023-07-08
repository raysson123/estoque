package br.edu.ifg.luziania.model.bo;

import br.edu.ifg.luziania.model.dao.DadosProdutoDAO;
import br.edu.ifg.luziania.model.dao.FornecedorDAO;
import br.edu.ifg.luziania.model.dao.ProdutosDAO;
import br.edu.ifg.luziania.model.dto.DadosProdutoDTO;
import br.edu.ifg.luziania.model.dto.RespostaDTO;
import br.edu.ifg.luziania.model.entity.DadosProduto;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Dependent
public class DadosProdutoBO{
    @Inject
    DadosProdutoDAO dadosProdutoDAO ;
    @Inject
    FornecedorDAO fornecedorDAO;
    @Inject
    ProdutosDAO produtosDAO;
    @Transactional
    public Response cadastradadosProdutos(DadosProdutoDTO[] dadosProdutoDTOS) {
        List<DadosProduto> listaDadosProduto = new ArrayList<>(); // Corrigido para usar List e ArrayList
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Movido a criação do SimpleDateFormat para fora do loop

        for (DadosProdutoDTO dadosProdutoDTO : dadosProdutoDTOS) {
            DadosProduto dadosProduto = new DadosProduto(); // Movido a criação do objeto DadosProduto para dentro do loop

            // Preenche os campos do objeto DadosProduto com os dados do DadosProdutoDTO
            dadosProduto.setProdutos(produtosDAO.buscarProdutoPorId(dadosProdutoDTO.getProdutosId()));
            dadosProduto.setFornecedor(fornecedorDAO.findById(dadosProdutoDTO.getFornecedorId()));
            dadosProduto.setPrecoDeCompra(dadosProdutoDTO.getPrecoDeCompra());
            dadosProduto.setNumeroDeSerie(dadosProdutoDTO.getNumeroDeSerie());
            dadosProduto.setModelo(dadosProdutoDTO.getModelo());
            dadosProduto.setQuantidadeDisponivel(dadosProdutoDTO.getQuantidadeDisponivel());

            try {
                dadosProduto.setDataDeAquisicao(sdf.parse(dadosProdutoDTO.getDataDeAquisicao()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            listaDadosProduto.add(dadosProduto); // Adiciona o objeto DadosProduto à lista

        }

        try {
            // Agora você precisa decidir o que deseja fazer com a lista de DadosProduto preenchida
            // Talvez você queira passar essa lista para o método cadastrarFornecedor() do fornecedorDAO
            dadosProdutoDAO.cadastrarDadosProdutos(listaDadosProduto);

            return Response.ok(new RespostaDTO(200, "Fornecedor cadastrado com sucesso!", "/dadosprodutos/cadatralist")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new RespostaDTO(500, "Falha ao cadastrar fornecedor!", "/"))
                    .build();
        }
    }


    public Response cadatradadosDadosP(DadosProdutoDTO dadosProdutoDTO) {
        DadosProduto dadosProduto = new DadosProduto();
        System.out.println(produtosDAO.buscarProdutoPorId(dadosProdutoDTO.getProdutosId()));

        dadosProduto.setPrecoDeCompra(dadosProdutoDTO.getPrecoDeCompra());

        try {
            dadosProdutoDAO.cadastrarProduto(dadosProduto);
            return Response.ok(new RespostaDTO(200, "Usuário salvo com sucesso!", "/")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new RespostaDTO(500, "Falha ao salvar usuário!", "/"))
                    .build();
        }
    }

}
