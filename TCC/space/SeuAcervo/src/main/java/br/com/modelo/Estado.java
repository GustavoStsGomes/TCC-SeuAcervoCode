package br.com.modelo;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="Estado")
@Table(name="ESTADO")
public class Estado {
	
	@Id
	@Column(name="SIGLA_ESTADO")
	private String sigla;
	
	@Column(name="NOME")
	private String nome;
		
	@OneToMany(fetch = FetchType.LAZY, mappedBy="Estado")
	private List<Cidade> listaCidade = new ArrayList<Cidade>();

	public Estado() {
	}
	
	public Estado(String sigla, String nome, List<Cidade> listaCidade) {
		super();
		this.sigla = sigla;
		this.nome = nome;
		this.listaCidade = listaCidade;
	}

	@Override
	public String toString() {
		return sigla;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listaCidade == null) ? 0 : listaCidade.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Estado))
			return false;
		Estado other = (Estado) obj;
		if (sigla == null){
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		return true;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cidade> getListaCidade() {
		return listaCidade;
	}

	public void setListaCidade(List<Cidade> listaCidade) {
		this.listaCidade = listaCidade;
	}

}
