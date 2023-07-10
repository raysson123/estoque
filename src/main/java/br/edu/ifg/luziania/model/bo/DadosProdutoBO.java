package br.edu.ifg.luziania.model.bo;

import br.edu.ifg.luziania.model.dao.*;
import br.edu.ifg.luziania.model.dto.DadosProdutoDTO;
import br.edu.ifg.luziania.model.dto.Dados_ProdutoDTO;
import br.edu.ifg.luziania.model.dto.RespostaDTO;
import br.edu.ifg.luziania.model.dto.UsarioRetiraProdutoDTO;
import br.edu.ifg.luziania.model.entity.DadosProduto;
import br.edu.ifg.luziania.model.entity.UsarioRetiraProdutos;
import br.edu.ifg.luziania.model.util.UserSession;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Dependent
public class DadosProdutoBO {
    @Inject
    UsarioRetiraProdutoDAO usarioRetiraProdutoDAO;
    @Inject
    DadosProdutoDAO dadosProdutoDAO;
    @Inject
    UserSession userSession;
    @Inject
    FornecedorDAO fornecedorDAO;
    @Inject
    UsuarioDAO usuarioDAO;
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

    public Response dadosDadosPLista() {
        List<DadosProduto> dadosProdutos = dadosProdutoDAO.listarTodos();
        List<Dados_ProdutoDTO> dados_produtoDTOS = new ArrayList<>();

        if (dadosProdutos != null) {
            for (DadosProduto dadosProduto : dadosProdutos) {
                // Criar e preencher o objeto DTO com os dados relevantes
                Dados_ProdutoDTO dto = new Dados_ProdutoDTO();
                // Preencha os campos do DTO com dados de dadosProduto
                // Exemplo: dto.setNome(dadosProduto.getNome());
                dto.setNumeroDeSerie(dadosProduto.getNumeroDeSerie());
                dto.setDataDeAquisicao(dadosProduto.getDataDeAquisicao());
                dto.setModelo(dadosProduto.getModelo());
                dto.setQuantidadeDisponivel(dadosProduto.getQuantidadeDisponivel());
                dto.setPrecoDeCompra(dadosProduto.getPrecoDeCompra());
                dto.setId(dadosProduto.getId());
                dto.setProdutosCartegoria(dadosProduto.getProdutos().getCategoria().getNomeCategoria());

                dto.setProdutosNome(dadosProduto.getProdutos().getNomeProdutos());
                dto.setFornecedorDTO(dadosProduto.getFornecedor());
                dados_produtoDTOS.add(dto);
            }

        }
        Jsonb jsonb = JsonbBuilder.create();
        String json = jsonb.toJson(dados_produtoDTOS);

        try {
            // Converter objeto em JSON usando JSON-B


            return Response.ok(json, MediaType.APPLICATION_JSON).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new RespostaDTO(500, "Falha ao listar categorias!", "/"))
                    .build();
        }
    }

    public Response retiradadeProdutos(UsarioRetiraProdutoDTO[] usarioRetiraProdutoDTOS) {
        List<UsarioRetiraProdutos> usarioRetiraProdutos = new ArrayList<>(); // Corrigido para usar List e ArrayList
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Movido a criação do SimpleDateFormat para fora do loop


        for (UsarioRetiraProdutoDTO usarioRetiraProdutoDTO : usarioRetiraProdutoDTOS) {
            UsarioRetiraProdutos usarioRetiraProdutos1 = new UsarioRetiraProdutos(); // Movido a criação do objeto DadosProduto para dentro do loop

            // Preenche os campos do objeto DadosProduto com os dados do DadosProdutoDTO
            usarioRetiraProdutos1.setDadosProduto(dadosProdutoDAO.buscarDadosProdutosPorId(usarioRetiraProdutoDTO.getDadosProdutoID()));
            usarioRetiraProdutos1.setUsuario(usuarioDAO.findById(userSession.getUserSessionDTO().getId()));
            usarioRetiraProdutos1.setDescricao(usarioRetiraProdutoDTO.getDescricao());
            System.out.println(usarioRetiraProdutoDTO.getQuatida());
            usarioRetiraProdutos1.setQuatida(usarioRetiraProdutoDTO.getQuatida());


            try {
                usarioRetiraProdutos1.setDataDeRetirada(sdf.parse(usarioRetiraProdutoDTO.getDataDeRetirada()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            usarioRetiraProdutos.add(usarioRetiraProdutos1); // Adiciona o objeto DadosProduto à lista

        }

        try {
            // Agora você precisa decidir o que deseja fazer com a lista de DadosProduto preenchida
            // Talvez você queira passar essa lista para o método cadastrarFornecedor() do fornecedorDAO
            usarioRetiraProdutoDAO.cadastrarDadosProdutos(usarioRetiraProdutos);

            return Response.ok(new RespostaDTO(200, "Fornecedor retirada com sucesso!", "/")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new RespostaDTO(500, "Falha ao  retirada de u errado!", "/"))
                    .build();
        }

    }


}
