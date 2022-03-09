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

@Entity(name="Categorias")
@Table(name="CATEGORIAS")
public class Categorias {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="IDCATEGORIAS")
	private int codCategorias;
	
	@Column(name="DESCRICAO")
	private String descricao;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="Categorias")
	private List<Armarios> listaArmarios = new ArrayList<Armarios>();
	
	public Categorias() {

	}

	public Categorias(int codCategorias, String descricao, List<Armarios> listaArmarios) {
		super();
		this.codCategorias = codCategorias;
		this.descricao = descricao;
		this.listaArmarios = listaArmarios;
	}

	@Override
	public String toString() {
		return descricao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codCategorias;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((listaArmarios == null) ? 0 : listaArmarios.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Categorias))
			return false;
		Categorias other = (Categorias) obj;
		if (descricao == null){
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

	public int getCodCategorias() {
		return codCategorias;
	}

	public void setCodCategorias(int codCategorias) {
		this.codCategorias = codCategorias;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Armarios> getListaArmarios() {
		return listaArmarios;
	}

	public void setListaArmarios(List<Armarios> listaArmarios) {
		this.listaArmarios = listaArmarios;
	}


}
