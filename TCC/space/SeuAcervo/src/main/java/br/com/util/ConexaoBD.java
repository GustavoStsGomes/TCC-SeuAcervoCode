package br.com.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ConexaoBD {
	
	//conseguimos manter ela enquanto a conexao estiver no ar//
	private static ConexaoBD conexao; 
	
	static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("SaPU");
	static EntityManager em = factory.createEntityManager();
	
	//para conferir se existe uma conexao ativa
	public static ConexaoBD getInstancia(){  
		
		if(conexao == null){
			conexao = new ConexaoBD();
		}
		return conexao;		
	}
	
	//efetivar o drive e passar os dados para conexao do banco
	public Connection pegaConexao(){
		Connection conexao = null;
		
		//sempre usar para fazer a tratativa do erro e para apresentar os blocos os erros
		try{
			Class.forName("com.mysql.jdbc.Driver");  //gerenciar o banco
			conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/SeuAcervo", "root", ""); //endere�o, usuario, senha, porta
			//conexao = DriverManager.getConnection("jdbc:mysql://187.120.153.243:3306/SeuAcervo", "acervous", "putaquepariu"); //endere�o, usuario, senha, porta
			
		}catch(ClassNotFoundException e){
			//vai printar o erro, apontando em qual vari�vel est� o erro
			e.printStackTrace(); 
		}catch(SQLException e){
			e.printStackTrace();
		}
		return conexao;
		
	}
	
	public EntityManager iniciaConexaoEM() {
		try {
			System.out.println("Realizando teste de reconexao...");
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			Query q = em.createNativeQuery("Select 1");
			q.getResultList();
		}catch(Exception e) {
			System.out.println("Havia problemas de conexao.\n"
					+ "O EntityManager sera reinstanciado.");
			em = factory.createEntityManager();
			return this.iniciaConexaoEM();
			
		}
		return em;
	} 
	
}