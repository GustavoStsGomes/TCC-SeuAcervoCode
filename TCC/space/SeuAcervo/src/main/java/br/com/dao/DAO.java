package br.com.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManager;

import br.com.modelo.Editora;
import br.com.util.ConexaoBD;

@ApplicationScoped
public class DAO {
	
	private EntityManager em;

	public DAO() {		
	}
	
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public void adiciona(Object obj) {
		try {
			em = ConexaoBD.getInstancia().iniciaConexaoEM();
			em.persist(obj);
			em.getTransaction().commit();
			em.close();
			
		}catch(Exception e){
			e.printStackTrace();
				em.getTransaction().rollback();
		}
	}
	
	public void remove(Object obj) {
		try {
			em = ConexaoBD.getInstancia().iniciaConexaoEM();
			em.remove(em.contains(obj) ? obj: em.merge(obj));
			em.getTransaction().commit();
			em.close();
			
		}catch(Exception e){
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}
	
	public void atualiza(Object obj) {
		try {
			em = ConexaoBD.getInstancia().iniciaConexaoEM();
			em.merge(obj);
			em.flush();
			em.getTransaction().commit();
			em.close();
			
		}catch(Exception e){
			e.printStackTrace();
				em.getTransaction().rollback();
		}
	}
	
	public List<?> listaTodos(Class<?> classe){
		try {
			em = ConexaoBD.getInstancia().iniciaConexaoEM();
			return em.createQuery("FROM " + classe.getName()).getResultList();

		}catch(Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			return null;
		}finally {
			em.close();
		}
	}
	
	public Object buscaPoId(Class<?> classe, Long id) {
		try {
			em = ConexaoBD.getInstancia().iniciaConexaoEM();
			return em.find(classe, id);
			
		}catch(Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			return null;
		}finally {
			em.close();
		}
	}
	
	public Editora buscaEditora(int id){
		
		Editora ed = new Editora();
		try{
			
			Connection conn = ConexaoBD.getInstancia().pegaConexao();
			String sqlBusca = "SELECT * FROM EDITORA WHERE IDEDITORA = ?";
			PreparedStatement ps = conn.prepareStatement(sqlBusca);
			ps.setInt(1, id);
			
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				ed = new Editora();
				ed.setCodEditora(resultSet.getInt("IDEDITORA"));
				ed.setDescricao(resultSet.getString("DESCRICAO"));
				ed.setImagem(resultSet.getString("IMAGEM"));				
				ed.setNome(resultSet.getString("NOME"));				
			}
			
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return ed;
		
	}
	
}
