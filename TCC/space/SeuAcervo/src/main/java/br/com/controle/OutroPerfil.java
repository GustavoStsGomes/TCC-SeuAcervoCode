package br.com.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dao.LivroDAO;
import br.com.modelo.Cidade;
import br.com.modelo.Livro;
import br.com.modelo.Usuario;

@ViewScoped
@ManagedBean(name="outPerfilControle")
public class OutroPerfil {
	
	private Usuario usuario;
	private Usuario perfil;
	private Cidade cidade;
	private List<Livro> livros;
	
	public OutroPerfil() {
		usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioSessao");
		perfil = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("perfilSessao");
		cidade = new Cidade();
		montarLista();
	}
	
	private List<Livro> montarLista() {
		try {
			livros = new ArrayList<Livro>();	
			LivroDAO lDAO = new LivroDAO();
			livros = (List<Livro>) lDAO.listaEspecifico(perfil);
			System.out.println("Tamanha controle:" + livros.size());
		}catch(Exception e){
			e.printStackTrace();		
		}
		return livros;
		
	}

	public void irEmail(){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("emailUsuario.xhtml");			
		}catch(Exception e){
			e.printStackTrace();
		}		
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

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidad) {
		cidade = cidad;
	}

	public Usuario getPerfil() {
		return perfil;
	}

	public void setPerfil(Usuario perfil) {
		this.perfil = perfil;
	}
	
}

