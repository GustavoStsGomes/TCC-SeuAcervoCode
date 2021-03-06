package br.com.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dao.CidadeDAO;
import br.com.dao.DAO;
import br.com.modelo.Cidade;
import br.com.modelo.Estado;
import br.com.modelo.Usuario;

@ViewScoped
@ManagedBean(name="novoControle")
public class NovoUsuarioControle {
	
	private Usuario usuario;
	private List<Usuario> listaUsuario = new ArrayList<Usuario>();
	private Cidade Cidade;
	private List<Cidade> listaCidade = new ArrayList<Cidade>();
	private List<Estado> listaEstado = new ArrayList<Estado>();
	public Estado estado;
	
	
	
	private DAO dAO = new DAO();
	
	public NovoUsuarioControle() {
		usuario = new Usuario();
		Cidade = new Cidade();
		carregaEstado();
	}
	
	public void cancelar() {
		usuario = new Usuario();
		Cidade = new Cidade();
	}
	
	public void trocarEst(Estado est) {
		estado = est;
		carregaCidade();
	}
	
	public void inserirUsuario(){
		
		usuario.setCidade(Cidade);
		usuario.setTipo("user");
		usuario.setPerfil((String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("imagemSessao"));
		
		try{
			dAO.adiciona(usuario);
			adicionarMensagem("Inserir Realizado com Sucesso", FacesMessage.SEVERITY_INFO);			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioSessao", usuario);			
			FacesContext.getCurrentInstance().getExternalContext().redirect("ListaLivros.xhtml");
							
		}catch(Exception e){
			e.printStackTrace();
			adicionarMensagem("Erro na inserção ou alteração de dados", FacesMessage.SEVERITY_ERROR);
		}
		usuario = new Usuario();
		Cidade = new Cidade();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("imagemSessao", "");
	}
	
	public void irLogin() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Cidade> carregaCidade(){
		try{
			listaCidade = new ArrayList<Cidade>();
			listaCidade = (List<Cidade>) dAO.listaTodos(Cidade.class);
		}catch(Exception e){
			e.printStackTrace();		
		}
		return listaCidade;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Estado> carregaEstado(){
		try{
			listaEstado = new ArrayList<Estado>();
			listaEstado = (List<Estado>) dAO.listaTodos(Estado.class);
		}catch(Exception e){
			e.printStackTrace();		
		}
		return listaEstado;
		
	}
	
	public void adicionarMensagem(String mensagem, FacesMessage.Severity tipoErro) {
		FacesMessage fm = new FacesMessage(tipoErro,mensagem,null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public Cidade getCidade() {
		return Cidade;
	}

	public void setCidade(Cidade cidade) {
		Cidade = cidade;
	}

	public List<Cidade> getListaCidade() {
		return listaCidade;
	}

	public void setListaCidade(List<Cidade> listaCidade) {
		this.listaCidade = listaCidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getListaEstado() {
		return listaEstado;
	}

	public void setListaEstado(List<Estado> listaEstado) {
		this.listaEstado = listaEstado;
	}

}
