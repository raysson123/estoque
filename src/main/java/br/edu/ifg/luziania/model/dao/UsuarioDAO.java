package br.edu.ifg.luziania.model.dao;

import br.edu.ifg.luziania.model.entity.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UsuarioDAO {
    @Inject
    EntityManager entityManager;

    @Transactional
    public void insert(Usuario usuario) {
        entityManager.persist(usuario);
    }

    @Transactional
    public void update(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Transactional
    public void delete(Usuario usuario) {
        entityManager.remove(usuario);
    }

    public Usuario findById(Long id) {
        return entityManager.find(Usuario.class, id);
    }
    public Usuario findByEmailAndSenha(String email, String senha) {
        Query query = entityManager.createQuery("from Usuario where email = :email and senha = :senha");
        query.setParameter("email", email);
        query.setParameter("senha", senha);
        try {
            return (Usuario) query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Caso nenhum usuário seja encontrado com o email e senha informados
        }
    }
    public List<Usuario> listarUsuarios() {
        return entityManager.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }
}
