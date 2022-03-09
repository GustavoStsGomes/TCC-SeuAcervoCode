package br.com.dao;

import javax.persistence.EntityManager;

import br.com.modelo.Categorias;
import br.com.modelo.Livro;
import br.com.modelo.Usuario;
import br.com.util.ConexaoBD;

public class ArmariosDAO {
	
	private EntityManager em;
	
	public Long verificaAdicao(Categorias cat, Usuario us, Livro lv) {
		Long quant;
		em = ConexaoBD.getInstancia().iniciaConexaoEM();
		quant = (Long)em.createQuery("SELECT COUNT(*) FROM Armarios a WHERE a.Categorias = :cat AND a.Usuario = :us AND a.Livro = :lv")
				.setParameter("cat", cat).setParameter("us", us).setParameter("lv", lv).getSingleResult();
		return quant;
	}


}
