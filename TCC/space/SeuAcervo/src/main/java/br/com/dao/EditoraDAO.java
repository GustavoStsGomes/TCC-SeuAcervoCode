package br.com.dao;

import javax.persistence.EntityManager;

import br.com.modelo.Editora;
import br.com.util.ConexaoBD;

public class EditoraDAO {
	
	private EntityManager em;
	
	public Long verificaExclusao(Editora codFK) {
		Long quant;
		em = ConexaoBD.getInstancia().iniciaConexaoEM();
		quant = (Long)em.createQuery("SELECT COUNT(*) FROM Livro l WHERE l.Editora = :id")
				.setParameter("id", codFK).getSingleResult();
		return quant;
	}

}
