package br.com.dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.Query;


import br.com.modelo.Usuario;
import br.com.util.ConexaoBD;


public class UsuarioDAO {
	
	private EntityManager em;

	public UsuarioDAO() {		
	}
	
	public Usuario logarHibernate (String email, String senha) {
		Usuario usu = new Usuario();
		try {
			em = ConexaoBD.getInstancia().iniciaConexaoEM();
			Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :login AND u.senha =:senha");
			q.setParameter("login", email);
			q.setParameter("senha", senha);
			usu = (Usuario)q.getSingleResult();
			em.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return usu;
		
	}
	
	//VERIFICA SE TEM LIGAÇÃO ANTES DE EXCLUIR
		public Long verificaExclusaoAR(Usuario codFK) {
			Long quant;
			em = ConexaoBD.getInstancia().iniciaConexaoEM();
			quant = (Long)em.createQuery("SELECT COUNT(*) FROM Armarios l WHERE l.Usuario = :id")
					.setParameter("id", codFK).getSingleResult();
			return quant;
		}
		
		public Long verificaExclusaoAV(Usuario codFK) {
			Long quant;
			em = ConexaoBD.getInstancia().iniciaConexaoEM();
			quant = (Long)em.createQuery("SELECT COUNT(*) FROM Avaliacao l WHERE l.Usuario = :id")
					.setParameter("id", codFK).getSingleResult();
			return quant;
		}

	
	//lista de user sem admin
	@SuppressWarnings("unchecked")
	public List<Usuario> listaDeUsuario(Class<Usuario> classe){
		try {
			List<Usuario> listaCompleta = new ArrayList<Usuario>();
			List<Usuario> list = new ArrayList<Usuario>();
			em = ConexaoBD.getInstancia().iniciaConexaoEM();
			listaCompleta = em.createQuery("FROM " + classe.getName()).getResultList();
			System.out.println("+++++++++++++++++++++++++ " + listaCompleta.size());
			for (int i = 0; i < listaCompleta.size(); i++) {
				System.out.println("dentro do for " + listaCompleta.get(i).getTipo());
				if(!listaCompleta.get(i).getTipo().equals("admin"))
					list.add(listaCompleta.get(i));
			}
			System.out.println("+++++++++++++++++++++++++ " + list.size());
			return list;

		}catch(Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			return null;
		}finally {
			em.close();
		}
	}
}
