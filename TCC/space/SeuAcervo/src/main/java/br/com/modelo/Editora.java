package br.com.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="Editora")
@Table(name="EDITORA")
public class Editora {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="IDEDITORA")
	private int codEditora;
	
	@Column(name="DESCRICAO")
	private String descricao;
	
	@Column(name="IMAGEM")
	private String imagem;
	
	@Column(name="NOME")
	private String nome;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="Editora")
	private List<Livro> listaLivro = new ArrayList<Livro>();

	public Editora() {
	}

	public Editora(int codEditora, String descricao, String imagem, String nome, List<Livro> listaLivro) {
		super();
		this.codEditora = codEditora;
		this.descricao = descricao;
		this.imagem = imagem;
		this.nome = nome;
		this.listaLivro = listaLivro;
	}

	@Override
	public String toString() {
		return nome;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codEditora;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((imagem == null) ? 0 : imagem.hashCode());
		result = prime * result + ((listaLivro == null) ? 0 : listaLivro.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Editora other = (Editora) obj;
		if (codEditora != other.codEditora)
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (imagem == null) {
			if (other.imagem != null)
				return false;
		} else if (!imagem.equals(other.imagem))
			return false;
		if (listaLivro == null) {
			if (other.listaLivro != null)
				return false;
		} else if (!listaLivro.equals(other.listaLivro))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public int getCodEditora() {
		return codEditora;
	}

	public void setCodEditora(int codEditora) {
		this.codEditora = codEditora;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Livro> getListaLivro() {
		return listaLivro;
	}

	public void setListaLivro(List<Livro> listaLivro) {
		this.listaLivro = listaLivro;
	}


}
