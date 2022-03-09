package br.com.modelo;

import java.util.ArrayList;
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

@Entity(name="Usuario")
@Table(name="USUARIO")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="IDUSUARIO")
	private int codUsuario;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="TIPO")
	private String tipo;
	
	@Column(name="SENHA")
	private String senha;
	
	@Column(name="TELEFONE")
	private String telefone;
	
	@Column(name="FOTO")
	private String perfil;
	
	@Column(name="LOGRADOURO")
	private String logradouro;
	
	@Column(name="COMPLEMENTO")
	private String complemento;
	
	@Column(name="BAIRRO")
	private String bairro;
	
	@Column(name="NUMERO")
	private int numero;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="IDCIDADE")
	private Cidade Cidade;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="Usuario")
	private List<Armarios> listaArmarios = new ArrayList<Armarios>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="Usuario")
	private List<Avaliacao> listaAvaliacao = new ArrayList<Avaliacao>();

	@Override
	public String toString() {
		return nome;
	}

	public Usuario() {
	}

	public Usuario(int codUsuario, String nome, String email, String tipo, String senha, String telefone, String perfil,
			String logradouro, String complemento, String bairro, int numero, br.com.modelo.Cidade cidade,
			List<Armarios> listaArmarios, List<Avaliacao> listaAvaliacao) {
		super();
		this.codUsuario = codUsuario;
		this.nome = nome;
		this.email = email;
		this.tipo = tipo;
		this.senha = senha;
		this.telefone = telefone;
		this.perfil = perfil;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.numero = numero;
		Cidade = cidade;
		this.listaArmarios = listaArmarios;
		this.listaAvaliacao = listaAvaliacao;
	}

	public int getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Cidade getCidade() {
		return Cidade;
	}

	public void setCidade(Cidade cidade) {
		Cidade = cidade;
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
