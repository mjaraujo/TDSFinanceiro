package br.edu.utfpr.giuvane.modelo.usuario;

import br.edu.utfpr.giuvane.modelo.dao.ConexaoHibernate;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;

public class UsuarioDAOHibernate implements UsuarioDAO {
    //private Session session;
    private EntityManager manager;
    
    public UsuarioDAOHibernate() {
        this.manager = ConexaoHibernate.getInstance();
    }

    public void setSession(Session session) {
        //this.session = session;
    }
    
    public void setManager(EntityManager manager) {
        this.manager = manager;
    }

    public void salvar(Usuario usuario) {
        //this.session.save(usuario);
        this.manager.persist(usuario);
    }

    public void atualizar(Usuario usuario) {
        if (usuario.getPermissao() == null || usuario.getPermissao().size() == 0) { 
                Usuario usuarioPermissao = this.carregar(usuario.getCodigo()); 
                usuario.setPermissao(usuarioPermissao.getPermissao()); 
                //this.session.evict(usuarioPermissao);

        }
        this.manager.persist(usuario);
    }

    public void excluir(Usuario usuario) {
        //this.session.delete(usuario);
        this.manager.remove(usuario);
    }

    public Usuario carregar(Integer codigo) {
        //return (Usuario) this.session.get(Usuario.class, codigo);
        return (Usuario) this.manager.find(Usuario.class, codigo);
    }

    public List<Usuario> listar() {
        String jpql = "select u from Usuario u";
        javax.persistence.Query query = manager.createQuery(jpql);
        List<Usuario> objects = query.getResultList();
        return objects;
        
    }

    public Usuario buscarPorLogin(String login) {
        String hql = "select u from Usuario u where u.login = :login";
        javax.persistence.Query consulta = this.manager.createQuery(hql);
        consulta.setParameter("login", login);
        return (Usuario) consulta.getSingleResult();
    }
}
