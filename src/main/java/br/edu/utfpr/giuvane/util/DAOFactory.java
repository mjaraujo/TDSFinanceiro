package br.edu.utfpr.giuvane.util;

import br.edu.utfpr.giuvane.modelo.usuario.UsuarioDAO;
import br.edu.utfpr.giuvane.modelo.usuario.UsuarioDAOHibernate;

public class DAOFactory {

	public static UsuarioDAO criarUsuarioDAO() {
		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		usuarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioDAO; 
	}
}
