package br.com.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.modelo.Cidade;
import br.com.modelo.Estado;
import br.com.util.ConexaoBD;

public class CidadeDAO {
	
	private EntityManager em;
	// Metodos Especificos

	// Lista de Livros de Acordo com a Editora
	@SuppressWarnings("unchecked")
	public List<Cidade> cidadeEstado(Estado uf) {
		try {
			em = ConexaoBD.getInstancia().iniciaConexaoEM();
			List<Cidade> list = new ArrayList<Cidade>();

			list = (List<Cidade>) em.createQuery("FROM Cidade c WHERE c.Estado = :id").setParameter("id", uf)
					.getResultList();
			System.out.println("++++++++++++++++++++++" + list.size());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("em");
			return null;
		}
	}

}
