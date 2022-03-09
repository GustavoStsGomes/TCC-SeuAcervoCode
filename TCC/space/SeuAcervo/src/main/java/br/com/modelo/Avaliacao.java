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

@Entity(name="Avaliacao")
@Table(name="AVALIACAO")
public class Avaliacao {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="IDAVALIACAO")
	private int codAvaliacao;
	
	@Column(name="DESCRICAO")
	private String descricao;
	
	@Column(name="NOTA")
	private int nota;
	
	@Column(name="DT_COMENTARIO")
	@Temporal(TemporalType.DATE)
	private Date dataComentario;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="IDLIVRO")
	private Livro Livro;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="IDUSUARIO")
	private Usuario Usuario;

	public Avaliacao() {

	}

	public Avaliacao(int codAvaliacao, String descricao, int nota, Date dataComentario, br.com.modelo.Livro livro,
			br.com.modelo.Usuario usuario) {
		super();
		this.codAvaliacao = codAvaliacao;
		this.descricao = descricao;
		this.nota = nota;
		this.dataComentario = dataComentario;
		Livro = livro;
		Usuario = usuario;
	}

	@Override
	public String toString() {
		return "Avaliacao [codArmarios=" + codAvaliacao + ", descricao=" + descricao + ", nota=" + nota
				+ ", dataComentario=" + dataComentario + ", Livro=" + Livro + ", Usuario=" + Usuario + "]";
	}

	public int getCodAvaliacao() {
		return codAvaliacao;
	}

	public void setCodAvaliacao(int codAvaliacao) {
		this.codAvaliacao = codAvaliacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public Date getDataComentario() {
		return dataComentario;
	}

	public void setDataComentario(Date dataComentario) {
		this.dataComentario = dataComentario;
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

}
