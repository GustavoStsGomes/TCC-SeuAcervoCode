package br.com.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="Livro")
@Table(name="LIVRO")
public class Livro {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="IDLIVRO")
	private int codLivro;
	
	@Column(name="AUTOR")
	private String autor;
	
	@Column(name="GENERO")
	private String genero;
	
	@Column(name="TITULO")
	private String titulo;
	
	@Column(name="ISBN")
	private String isbn;
	
	@Column(name="CAPA")
	private String capa;
	
	@Column(name="SINOPSE")
	private String sinopse;
	
	@Column(name="DT_ADICIONADO")
	private Date dataAdicionado;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="IDEDITORA")
	private Editora Editora;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="Livro")
	private List<Armarios> listaArmarios = new ArrayList<Armarios>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="Livro")
	private List<Avaliacao> listaAvaliacao = new ArrayList<Avaliacao>();

	@Override
	public String toString() {
		return titulo;
	}
	
	public Livro() {
	}

	public Livro(int codLivro, String autor, String genero, String titulo, String isbn, String capa, String sinopse,
			Date dataAdicionado, br.com.modelo.Editora editora, List<Armarios> listaArmarios,
			List<Avaliacao> listaAvaliacao) {
		super();
		this.codLivro = codLivro;
		this.autor = autor;
		this.genero = genero;
		this.titulo = titulo;
		this.isbn = isbn;
		this.capa = capa;
		this.sinopse = sinopse;
		this.dataAdicionado = dataAdicionado;
		Editora = editora;
		this.listaArmarios = listaArmarios;
		this.listaAvaliacao = listaAvaliacao;
	}

	public int getCodLivro() {
		return codLivro;
	}

	public void setCodLivro(int codLivro) {
		this.codLivro = codLivro;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getCapa() {
		return capa;
	}

	public void setCapa(String capa) {
		this.capa = capa;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public Date getDataAdicionado() {
		return dataAdicionado;
	}

	public void setDataAdicionado(Date dataAdicionado) {
		this.dataAdicionado = dataAdicionado;
	}

	public Editora getEditora() {
		return Editora;
	}

	public void setEditora(Editora editora) {
		Editora = editora;
	}

	public List<Armarios> getListaArmarios() {
		return listaArmarios;
	}

	public void setListaArmarios(List<Armarios> listaArmarios) {
		this.listaArmarios = listaArmarios;
	}

	public List<Avaliacao> getListaAvaliacao() {
		return listaAvaliacao;
	}

	public void setListaAvaliacao(List<Avaliacao> listaAvaliacao) {
		this.listaAvaliacao = listaAvaliacao;
	}

}
