package br.com.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dao.DAO;
import br.com.modelo.Categorias;
import br.com.modelo.Cidade;
import br.com.modelo.Livro;
import br.com.modelo.Usuario;

@ViewScoped
@ManagedBean(name = "perfilControle")
public class PerfilControle {

	private Usuario usuario;
	private List<Usuario> listaUsuario;
	private Cidade cidade;
	private Categorias categorias;
	private List<Categorias> listaCategorias = new ArrayList<Categorias>();
	private List<Livro> livros;

	private DAO dAO = new DAO();

	public PerfilControle() {
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioSessao");
		listaUsuario = new ArrayList<Usuario>();
		categorias = new Categorias();
		cidade = new Cidade();
		carregaCategorias();
		
	}

	public void alterarUsuario() {

		usuario.setCidade(cidade);
		usuario.setPerfil(
				(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("imagemSessao"));

		try {
			if (usuario.getCodUsuario() != 0) {
				dAO.atualiza(usuario);
				adicionarMensagem("Alteração Realizada", FacesMessage.SEVERITY_INFO);

			} else {
				adicionarMensagem("Erro na alteração de dados", FacesMessage.SEVERITY_ERROR);

			}
			// limpar formulario
		} catch (Exception e) {
			e.printStackTrace();
			adicionarMensagem("Erro na inserção ou alteração de dados", FacesMessage.SEVERITY_ERROR);
		}
		categorias = new Categorias();
		cidade = new Cidade();
		carregaCategorias();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioSessao", usuario);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("imagemSessao", "");
	}

	public void irMeuPerfil() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("meuPerfil.xhtml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void irLogin() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void irArmario(Categorias c) {
		categorias = c;
		try {

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("categoriasSessao", categorias);
			FacesContext.getCurrentInstance().getExternalContext().redirect("ListaArmarios.xhtml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Categorias> carregaCategorias() {
		try {
			listaCategorias = new ArrayList<Categorias>();
			listaCategorias = (List<Categorias>) dAO.listaTodos(Categorias.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaCategorias;

	}

	public void adicionarMensagem(String mensagem, FacesMessage.Severity tipoErro) {
		FacesMessage fm = new FacesMessage(tipoErro, mensagem, null);
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

	public Categorias getCategorias() {
		return categorias;
	}

	public void setCategorias(Categorias categorias) {
		this.categorias = categorias;
	}

	public List<Categorias> getListaCategorias() {
		carregaCategorias();
		return listaCategorias;
	}

	public void setListaCategorias(List<Categorias> listaCategorias) {
		this.listaCategorias = listaCategorias;
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

}
