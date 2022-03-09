package br.com.controle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dao.DAO;
import br.com.dao.LivroDAO;
import br.com.modelo.Armarios;
import br.com.modelo.Categorias;
import br.com.modelo.Editora;
import br.com.modelo.Livro;
import br.com.modelo.Usuario;

@ViewScoped
@ManagedBean(name="livroControle")
public class LivroControle {
	
	private Livro livro;
	private List<Livro> listaLivro = new ArrayList<Livro>();
	private Editora Editora;
	private List<Editora> listaEditora = new ArrayList<Editora>();
	private Usuario Usuario = new Usuario();
	private List<Categorias> listaCategorias = new ArrayList<Categorias>();
	private Armarios armarios;
 
	private DAO dAO = new DAO();
		
	public LivroControle() {
		livro = new Livro();
		Editora = new Editora();
		Usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioSessao");
		armarios = new Armarios();
		buscaLivro();
	}
	
	public void cancelar() {
		livro = new Livro();
		Editora = new Editora();
	}
	
	public void inserirLivro(){
		
		livro.setEditora(Editora);
		livro.setCapa((String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("imagemSessao"));
		
		Date data = new Date(System.currentTimeMillis());  
		SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd"); 	
		System.out.print(formatarDate.format(data));
		livro.setDataAdicionado(data);
		
		try{			
			if(livro.getCodLivro() != 0){
				dAO.atualiza(livro);
				adicionarMensagem("Alteração Realizada", FacesMessage.SEVERITY_INFO);
				
			}else{
				dAO.adiciona(livro);
				adicionarMensagem("Inserir Realizado com Sucesso", FacesMessage.SEVERITY_INFO);
			}
			//limpar formulario						
		}catch(Exception e){
			e.printStackTrace();
			adicionarMensagem("Erro na inserção ou alteração de dados", FacesMessage.SEVERITY_ERROR);
		}
		livro = new Livro();
		Editora = new Editora();
		Usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioSessao");
		buscaLivro();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("imagemSessao", "");
	}
		
	public void excluirLivro(Livro c){
		
		livro =c;
		LivroDAO lDao = new LivroDAO();
		try{
			if(lDao.verificaExclusaoAR(livro)==0 && lDao.verificaExclusaoAV(livro)==0) {
				listaLivro = buscaLivro();
				dAO.remove(livro);			
				adicionarMensagem("Excluido com sucesso! ", FacesMessage.SEVERITY_INFO);
			}else {
				adicionarMensagem("Impossivel excluir, objeto possui ligações", FacesMessage.SEVERITY_INFO);
			}
						
		}catch(Exception e){
			e.printStackTrace();
			adicionarMensagem("Erro na exclusão ", FacesMessage.SEVERITY_ERROR);
		}
		buscaLivro();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Livro> buscaLivro(){
		try{
			listaLivro = new ArrayList<Livro>();
			listaLivro = (List<Livro>) dAO.listaTodos(Livro.class);
		}catch(Exception e){
			e.printStackTrace();		
		}
		return listaLivro;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Editora> carregaEditora(){
		try{
			listaEditora = new ArrayList<Editora>();
			listaEditora = (List<Editora>) dAO.listaTodos(Editora.class);
		}catch(Exception e){
			e.printStackTrace();		
		}
		return listaEditora;
		
	}
	
	public void carregaFormulario(Livro l){
		
		try{
			
			livro = l;
			System.out.println("Entrou no carraga Formulario");
			
		}catch(Exception e){
			e.printStackTrace();
			adicionarMensagem("Erro na Alteração", FacesMessage.SEVERITY_ERROR);
		}
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
	
	
	
	public void adicionarMensagem(String mensagem, FacesMessage.Severity tipoErro) {
		FacesMessage fm = new FacesMessage(tipoErro,mensagem,null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Livro> getListaLivro() {
		return listaLivro;
	}

	public void setListaLivro(List<Livro> listaLivro) {
		buscaLivro();
		this.listaLivro = listaLivro;
	}

	public Editora getEditora() {
		return Editora;
	}

	public void setEditora(Editora editora) {
		this.Editora = editora;
	}

	public List<Editora> getListaEditora() {
		return listaEditora;
	}

	public void setListaEditora(List<Editora> listaEditora) {
		this.listaEditora = listaEditora;
	}
	
	public Usuario getUsuario() {
		return Usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.Usuario = usuario;
	}

	public List<Categorias> getListaCategorias() {
		carregaCategorias();
		return listaCategorias;
	}

	public void setListaCategorias(List<Categorias> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}

	public Armarios getArmarios() {
		return armarios;
	}

	public void setArmarios(Armarios armarios) {
		this.armarios = armarios;
	}
	
	


}
