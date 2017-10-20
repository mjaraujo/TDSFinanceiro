package br.edu.utfpr.giuvane.modelo.conta;

import br.edu.utfpr.giuvane.modelo.dao.ConexaoHibernate;
import br.edu.utfpr.giuvane.modelo.usuario.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.*;

public class ContaDAOHibernate implements ContaDAO {
    //private Session session;
    private EntityManager manager;
    
    public ContaDAOHibernate() {
        this.manager = ConexaoHibernate.getInstance();
    }
    
    public void setSession(Session session) {
        //this.session = session;
    }
    public void excluir(Conta conta) {
        this.manager.remove(conta);
    }
    public void salvar(Conta conta) { 
        this.manager.persist(conta);
    }
    public Conta carregar(Integer conta) {
        return (Conta) this.manager.find(Conta.class, conta);
    }
    public List<Conta> listar(Usuario usuario) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Conta> c = cb.createQuery(Conta.class);
        Root<Conta> a = c.from(Conta.class);
        c.select(a);
        
        Predicate predicate = cb.equal(a.get("usuario"), usuario);
        c.where(predicate);
        
        TypedQuery<Conta> query = this.manager.createQuery(c);
        List<Conta> contas = query.getResultList();
        return contas;
        
        //Criteria criteria = this.manager.createCriteria(Conta.class); 
        //criteria.add(Restrictions.eq("usuario", usuario)); 
        //return criteria.list();
    }
    public Conta buscarFavorita(Usuario usuario) { 
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Conta> c = cb.createQuery(Conta.class);
        Root<Conta> a = c.from(Conta.class);
        c.select(a);   
        
        Predicate predicate = cb.equal(a.get("usuario"), usuario);
        c.where(predicate);
        Predicate predicate2 = cb.equal(a.get("favorita"), true);
        c.where(predicate2);
        
        TypedQuery<Conta> query = this.manager.createQuery(c);
        Conta conta = query.getSingleResult();
        return conta;
        
        //Criteria criteria = this.session.createCriteria(Conta.class);
        //criteria.add(Restrictions.eq("usuario", usuario));
        //criteria.add(Restrictions.eq("favorita", true));
        //return (Conta) criteria.uniqueResult();	
    }
}
