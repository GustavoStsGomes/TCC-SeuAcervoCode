package br.com.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dao.LivroDAO;
import br.com.modelo.Editora;
import br.com.modelo.Livro;
import br.com.modelo.Usuario;

@ViewScoped
@ManagedBean(name="livroPorEditoraControle")
public class LivrosPorEditoraControle {
	
	private Editora editora = new Editora();
	private List<Livro> livrosPorEditora;
	private Usuario Usuario = new Usuario();
	private Livro livro;
	
	public LivrosPorEditoraControle() {
		editora = (Editora)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("editoraSessao");
		Usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioSessao");
		buscaLista();
	}
	
	private List<Livro> buscaLista() {	
		try {
			livrosPorEditora = new ArrayList<Livro>();	
			LivroDAO lDAO = new LivroDAO();
			livrosPorEditora = (List<Livro>) lDAO.LivrosPorEditora(editora);
			System.out.println("Tamanha controle:" + livrosPorEditora.size());
		}catch(Exception e){
			e.printStackTrace();		
		}
		return livrosPorEditora;			
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
	
	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public List<Livro> getLivrosPorEditora() {
		return livrosPorEditora;
	}

	public void setLivrosPorEditora(List<Livro> livrosPorEditora) {
		this.livrosPorEditora = livrosPorEditora;
	}

	public Usuario getUsuario() {
		return Usuario;
	}

	public void setUsuario(Usuario usuario) {
		Usuario = usuario;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
		
}
