package br.edu.utfpr.giuvane.web.filter;

import br.edu.utfpr.giuvane.modelo.dao.ConexaoHibernate;
import br.edu.utfpr.giuvane.util.HibernateUtil;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@WebFilter(urlPatterns = { "*.jsf" })
public class ConexaoHibernateFilter implements Filter {
	//private SessionFactory sf;
    private EntityManager manager;

	public void init(FilterConfig config) throws ServletException {
		//this.sf = HibernateUtil.getSessionFactory();
                this.manager = ConexaoHibernate.getInstance();
	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws ServletException {

		//Session currentSession = this.sf.getCurrentSession();
                

		//Transaction transaction = null;
                //EntityTransaction transaction = null;

		try {
                    //transaction = currentSession.beginTransaction();
                    //transaction = this.manager.getTransaction();
                    this.manager.getTransaction().begin();
                    chain.doFilter(servletRequest, servletResponse);
                    //transaction.commit();
                     this.manager.getTransaction().commit();
                     //this.manager.close();

                    if (this.manager.getTransaction().isActive()) {
                            this.manager.close();
                    }
		} catch (Throwable ex) {
                    
                    try {
                        if (this.manager.getTransaction().isActive()) {
                            this.manager.getTransaction().rollback();
                    }   
                        /*
                            if (transaction.isActive()) {
                                    transaction.rollback();
                            }
                        */
                    } catch (Throwable t) {
                            t.printStackTrace();
                    }
                    throw new ServletException(ex);
                        
		}
	}

	public void destroy() {
	}
}
