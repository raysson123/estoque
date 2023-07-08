package br.edu.ifg.luziania.model.bo;

import br.edu.ifg.luziania.model.dao.UsuarioDAO;
import br.edu.ifg.luziania.model.dto.RespostaDTO;
import br.edu.ifg.luziania.model.dto.RetornoDTO;
import br.edu.ifg.luziania.model.dto.UserSessionDTO;
import br.edu.ifg.luziania.model.dto.UsuarioDTO;
import br.edu.ifg.luziania.model.entity.Usuario;

import br.edu.ifg.luziania.model.util.Sessao;
import br.edu.ifg.luziania.model.util.TipoUsuario;

import br.edu.ifg.luziania.model.util.UserSession;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import jakarta.ws.rs.core.Response;


@Dependent
public class UsuarioBO {
    @Inject
    UserSession userSession;

    @Inject
    Sessao sessao;
    @Inject
    UsuarioDAO usuarioDAO;



    public Response autenticar(UsuarioDTO usuarioDTO) {
        // Verifica se os dados obrigatórios estão presentes
        if (usuarioDTO == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados obrigatórios não presentes!").build();
        }

        // Busca o usuário no banco de dados com base no email e senha fornecidos
        Usuario usuario = usuarioDAO.findByEmailAndSenha(usuarioDTO.getEmail(), usuarioDTO.getSenha());

        if (usuario != null) {
            // Cria um objeto UserSessionDTO com os dados do usuário autenticado
            UserSessionDTO userSessionDTO = new UserSessionDTO();
            userSessionDTO.setNomeUsuario(usuario.getNomeUsuario());
            userSessionDTO.setId(usuario.getId());
            userSessionDTO.setTipoUsuario(usuario.getTipoUsuario());

            // Define o UserSessionDTO na instância de UserSession
            userSession.setUserSessionDTO(userSessionDTO);
            Jsonb jsonb = JsonbBuilder.create();
            String json = jsonb.toJson(userSession.getUserSessionDTO().getNomeUsuario());

            // Gera um novo token ou utiliza o token existente, se válido
            if (!userSession.isTokenValid()) {
                userSession.generateToken();
                userSession.updateExpirationTime();
            }
            String token = userSession.getToken();

            // Retorna uma resposta de sucesso com uma mensagem de boas-vindas e o token no cabeçalho
            return Response.ok(new RetornoDTO("Bem vindo " + usuario.getNomeUsuario() + "!"))
                    .header("Authorization",token)
                    .entity(json)
                    .build();
        } else {
            // Retorna uma resposta de usuário não encontrado
            return Response.status(Response.Status.NOT_FOUND).entity(new RetornoDTO("Usuário não encontrado!")).build();
        }
    }



    public Response logout() {
        userSession.clearSession();
        return Response.ok(new RespostaDTO(200, "Usuário saiu", "/")).build();
    }


    public Response cadastrarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setNomeUsuario(usuarioDTO.getNome());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setTipoUsuario(TipoUsuario.GERENTE); // Definir o tipo de usuário, por exemplo, ADMIN
        System.out.println(usuarioDTO.getEmail());
        try {
            usuarioDAO.insert(usuario);
            return Response.ok(new RespostaDTO(200, "Usuário salvo com sucesso!", "/")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new RespostaDTO(500, "Falha ao salvar usuário!", "/"))
                    .build();
        }
    }



    public void mostarUsuario() {


    }



   /* public Response todosUsuarios() {

    }*/

    public String validar(String url) {
        if (userSession.isSessionActive())
            return url;

        return "1";
    }
}


