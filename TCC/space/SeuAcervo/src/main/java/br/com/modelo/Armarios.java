package br.com.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="Armarios")
@Table(name="ARMARIOS")
public class Armarios {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="IDARMARIOS")
	private int codArmarios;
	
	@Column(name="DT_SALVO")
	@Temporal(TemporalType.DATE)
	private Date dataSalvo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="IDLIVRO")
	private Livro Livro;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="IDUSUARIO")
	private Usuario Usuario;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="IDCATEGORIAS")
	private Categorias Categorias;

	public Armarios() {

	}

	public Armarios(int codArmarios, Date dataSalvo, br.com.modelo.Livro livro, br.com.modelo.Usuario usuario,
			br.com.modelo.Categorias categorias) {
		super();
		this.codArmarios = codArmarios;
		this.dataSalvo = dataSalvo;
		Livro = livro;
		Usuario = usuario;
		Categorias = categorias;
	}


	public int getCodArmarios() {
		return codArmarios;
	}

	public void setCodArmarios(int codArmarios) {
		this.codArmarios = codArmarios;
	}

	public Date getDataSalvo() {
		return dataSalvo;
	}

	public void setDataSalvo(Date dataSalvo) {
		this.dataSalvo = dataSalvo;
	}

	public Livro getLivro() {
		return Livro;
	}

	public void setLivro(Livro livro) {
		Livro = livro;
	}

	public Usuario getUsuario() {
		return Usuario;
	}

	public void setUsuario(Usuario usuario) {
		Usuario = usuario;
	}

	public Categorias getCategorias() {
		return Categorias;
	}

	public void setCategorias(Categorias categorias) {
		Categorias = categorias;
	}

}
