package br.com.dao;

import javax.persistence.EntityManager;

import br.com.modelo.Categorias;
import br.com.util.ConexaoBD;

public class CategoriasDAO {
	
	private EntityManager em;
	
	public Long verificaExclusaoCat(Categorias codFK) {
		Long quant;
		em = ConexaoBD.getInstancia().iniciaConexaoEM();
		quant = (Long)em.createQuery("SELECT COUNT(*) FROM Armarios l WHERE l.Categorias = :id")
				.setParameter("id", codFK).getSingleResult();
		return quant;
	}


}
