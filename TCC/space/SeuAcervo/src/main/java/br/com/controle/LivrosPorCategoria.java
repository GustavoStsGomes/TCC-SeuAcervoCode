package br.com.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dao.DAO;
import br.com.dao.LivroDAO;
import br.com.modelo.Armarios;
import br.com.modelo.Categorias;
import br.com.modelo.Livro;
import br.com.modelo.Usuario;

@ViewScoped
@ManagedBean(name="livrosPorCategoriasControle")
public class LivrosPorCategoria {
	
	private Usuario usuario;
	private Categorias categorias = new Categorias();;
	private List<Categorias> listaCategorias;
	private Livro livro;
	private List<Armarios> listaLivro = new ArrayList<Armarios>();
	private Armarios armarios;
	
	private DAO dAO = new DAO();
	
	public LivrosPorCategoria() {
		armarios = new Armarios();
		livro = new Livro();
		usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioSessao");
		categorias  = (Categorias)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("categoriasSessao");
	}

	public void comentario(Livro lv) {				
		try {
			livro = lv;			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("livroSessao", livro);
			FacesContext.getCurrentInstance().getExternalContext().redirect("comentario.xhtml");			
		}catch(Exception e){
			e.printStackTrace();		
		}			
	}

	public void excluirArmarios(Armarios u){
		
		armarios = u;
		try{
			listaLivro = buscaArmarios();
			dAO.remove(armarios);
			
			adicionarMensagem("Excluído com sucesso! ", FacesMessage.SEVERITY_INFO);			
		}catch(Exception e){
			e.printStackTrace();
			adicionarMensagem("Erro na exclusão ", FacesMessage.SEVERITY_ERROR);
		}
		buscaArmarios();
	}
	
	@SuppressWarnings("unchecked")
	public List<Categorias> carregaCategorias(){	
		try{
			listaCategorias = new ArrayList<Categorias>();
			listaCategorias = (List<Categorias>) dAO.listaTodos(Categorias.class);
		}catch(Exception e){
			e.printStackTrace();		
		}
		return listaCategorias;
		
	}
	
	public List<Armarios> buscaArmarios(){

		try{
			listaLivro = new ArrayList<Armarios>();
			LivroDAO dao = new LivroDAO();
			System.out.println("++++++++++++++++++++++++++ Usuario:" + usuario.getNome());
			System.out.println("+++++++++++++++++++++++++++ Categoria:" + categorias.getDescricao());
			listaLivro = dao.LivrosPorCategoria(usuario, categorias);
			System.out.println("*************************** Tamanho controle:" + listaLivro.size());
			//System.out.println("*************************** primeiro da lista:" + listaLivro.get(0).getTitulo());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return listaLivro;
		
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

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Armarios> getListaLivro() {
		buscaArmarios();
		return listaLivro;
	}

	public void setListaLivro(List<Armarios> listaLivro) {
		this.listaLivro = listaLivro;
	}

}
