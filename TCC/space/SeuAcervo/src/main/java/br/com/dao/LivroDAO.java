package br.com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.modelo.Armarios;
import br.com.modelo.Avaliacao;
import br.com.modelo.Categorias;
import br.com.modelo.Editora;
import br.com.modelo.Livro;
import br.com.modelo.Usuario;
import br.com.util.ConexaoBD;

public class LivroDAO {
	
	private EntityManager em;
	
	//Metodos Especificos
	
	// Lista de Livros de Acordo com a Editora
	@SuppressWarnings("unchecked")
	public List<Livro> LivrosPorEditora(Editora ed) {
		try {
			em = ConexaoBD.getInstancia().iniciaConexaoEM();
			List<Livro> list = new ArrayList<Livro>();

			list = (List<Livro>) em.createQuery("FROM Livro l WHERE l.Editora = :id").setParameter("id", ed)
					.getResultList();
			System.out.println("++++++++++++++++++++++" + list.size());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("em");
			return null;
		}
	}

	// lista de avaliacao por livro
	@SuppressWarnings("unchecked")
	public List<Avaliacao> avaliacaoPorLivro(Livro liv) {
		try {
			em = ConexaoBD.getInstancia().iniciaConexaoEM();
			List<Avaliacao> list = new ArrayList<Avaliacao>();

			list = (List<Avaliacao>) em.createQuery("FROM Avaliacao l WHERE l.Livro = :id").setParameter("id", liv)
					.getResultList();
			System.out.println("avaliacaoPorLivro" + list.size());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("em");
			return null;
		}
	}

	// Busca a Lista de Favoritos do outro usuario para exibir no perfil
	public List<Livro> listaEspecifico(Usuario u) {

		System.out.println("+++++++++++++++++++++++++++ Usuario no DAO:" + u.getCodUsuario());
		System.out.println("+++++++++++++++++++++++++++ Usuario no DAO:" + u.getNome());

		List<Livro> listaLivros = new ArrayList<>();
		try {
			Connection conn = ConexaoBD.getInstancia().pegaConexao();
			String sqlBuscaTodos = "SELECT l.* FROM LIVRO l, ARMARIOS a, USUARIO u, CATEGORIAS c WHERE "
					+ "l.IDLIVRO = a.IDLIVRO AND a.IDCATEGORIAS = c.IDCATEGORIAS AND a.IDCATEGORIAS = 10 AND "
					+ "a.IDUSUARIO = u.IDUSUARIO AND u.IDUSUARIO = ? ";
			PreparedStatement ps = conn.prepareStatement(sqlBuscaTodos);
			ps.setInt(1, u.getCodUsuario());

			ResultSet resultSet = ps.executeQuery();
			Editora ed = new Editora();
			while (resultSet.next()) {
				Livro liv = new Livro();
				liv.setCodLivro(resultSet.getInt("IDLIVRO"));
				liv.setAutor(resultSet.getString("AUTOR"));
				liv.setGenero(resultSet.getString("GENERO"));
				liv.setTitulo(resultSet.getString("TITULO"));
				liv.setCapa(resultSet.getString("CAPA"));
				liv.setSinopse(resultSet.getString("SINOPSE"));
				liv.setIsbn(resultSet.getString("ISBN"));

				Date utilDate = resultSet.getDate("DT_ADICIONADO");
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				liv.setDataAdicionado((java.sql.Date) sqlDate);

				DAO eDAO = new DAO();
				ed = eDAO.buscaEditora(resultSet.getInt("IDEDITORA"));
				liv.setEditora(ed);

				listaLivros.add(liv);
			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaLivros;
	}

	// Livros por Categoria
	@SuppressWarnings("unchecked")
	public List<Armarios> LivrosPorCategoria(Usuario u, Categorias c) {

		System.out.println("+++++++++++++++++++++++++++ Usuario no DAO:" + u.getCodUsuario());
		System.out.println("+++++++++++++++++++++++++++ Usuario no DAO:" + u.getNome());
		System.out.println("+++++++++++++++++++++++++++ Categoria no DAO:" + c.getDescricao());
		System.out.println("+++++++++++++++++++++++++++ Categoria no DAO:" + c.getCodCategorias());

		// ("FROM Livro l WHERE l.Editora = :id")
		try {
			em = ConexaoBD.getInstancia().iniciaConexaoEM();
			List<Armarios> list = new ArrayList<Armarios>();

			list = (List<Armarios>) em.createQuery("FROM Armarios a WHERE a.Categorias = :cat AND a.Usuario = :user ")
					.setParameter("cat", c).setParameter("user", u).getResultList();
			System.out.println("++++++++++++++++++++++ Tamanho DAO" + list.size());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//VERIFICA SE TEM LIGAÇÃO ANTES DE EXCLUIR
	public Long verificaExclusaoAR(Livro codFK) {
		Long quant;
		em = ConexaoBD.getInstancia().iniciaConexaoEM();
		quant = (Long)em.createQuery("SELECT COUNT(*) FROM Armarios l WHERE l.Livro = :id")
				.setParameter("id", codFK).getSingleResult();
		return quant;
	}
	
	public Long verificaExclusaoAV(Livro codFK) {
		Long quant;
		em = ConexaoBD.getInstancia().iniciaConexaoEM();
		quant = (Long)em.createQuery("SELECT COUNT(*) FROM Avaliacao l WHERE l.Livro = :id")
				.setParameter("id", codFK).getSingleResult();
		return quant;
	}

}
