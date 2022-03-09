package br.com.controle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dao.ArmariosDAO;
import br.com.dao.DAO;
import br.com.dao.LivroDAO;
import br.com.modelo.Armarios;
import br.com.modelo.Avaliacao;
import br.com.modelo.Categorias;
import br.com.modelo.Livro;
import br.com.modelo.Usuario;

@ViewScoped
@ManagedBean(name="comentarioControle")
public class comentarioControle {
	
	private Avaliacao avaliacao;
	private List<Avaliacao> avaliacoesPorLivro = new ArrayList<Avaliacao>();
	private Usuario Usuario = new Usuario();
	private List<Usuario> listaUsuario = new ArrayList<Usuario>();
	private Livro Livro = new Livro();
	private List<Livro> listaLivro = new ArrayList<Livro>();
	private Categorias categorias = new Categorias();
	private List<Categorias> listaCategorias = new ArrayList<Categorias>();
	private Armarios armarios = new Armarios();
	
	private DAO dAO = new DAO();
		
	public comentarioControle() {
		avaliacao = new Avaliacao();
		Usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioSessao");
		Livro = (Livro)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("livroSessao");
		buscaListaAL();
	}
	
	private List<Avaliacao> buscaListaAL() {
		try {
			avaliacoesPorLivro = new ArrayList<Avaliacao>();
			
			LivroDAO lDAO = new LivroDAO();
			avaliacoesPorLivro = (List<Avaliacao>) lDAO.avaliacaoPorLivro(Livro);
			
			System.out.println("Tamanha controle:" + avaliacoesPorLivro.size());
	
		}catch(Exception e){
			e.printStackTrace();		
		}
		return avaliacoesPorLivro;
		
	}

	public void cancelar() {
		avaliacao = new Avaliacao();
	}
	
	public void inserirAvaliacao(){
		
		avaliacao.setUsuario(Usuario);
		avaliacao.setLivro(Livro);		
						
		Date data = new Date(System.currentTimeMillis());  
		SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd"); 	
		System.out.print(formatarDate.format(data));
		avaliacao.setDataComentario(data);
		
		try{			
			if(avaliacao.getCodAvaliacao() != 0){
				dAO.atualiza(avaliacao);
				adicionarMensagem("Alteração Realizada", FacesMessage.SEVERITY_INFO);
				
			}else{
				dAO.adiciona(avaliacao);
				adicionarMensagem("Inserir Realizado com Sucesso", FacesMessage.SEVERITY_INFO);
			}
			//limpar formulario						
		}catch(Exception e){
			e.printStackTrace();
			adicionarMensagem("Erro na inserção ou alteração de dados", FacesMessage.SEVERITY_ERROR);
		}
		avaliacao = new Avaliacao();
		Usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioSessao");
		Livro = (Livro)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("livroSessao");
		buscaListaAL();
	}
	
	public void inserirArmarios(){
		
		armarios.setUsuario(Usuario);
		armarios.setLivro(Livro);
		armarios.setCategorias(categorias);
		
		Date data = new Date(System.currentTimeMillis());  
		SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd"); 	
		System.out.print(formatarDate.format(data));
		armarios.setDataSalvo(data);
		
		ArmariosDAO aDAO = new ArmariosDAO();		
		try{
			if(aDAO.verificaAdicao(categorias, Usuario, Livro)==0) {
				dAO.adiciona(armarios);
				adicionarMensagem("Inserir Realizado com Sucesso", FacesMessage.SEVERITY_INFO);
			}else {
				adicionarMensagem("Não foi possivel incluir, livro ja esta no armario", FacesMessage.SEVERITY_ERROR);
			}
			
			//limpar formulario						
		}catch(Exception e){
			e.printStackTrace();
			adicionarMensagem("Erro na inserção ou alteração de dados", FacesMessage.SEVERITY_ERROR);
		}
		armarios = new Armarios();
		Usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioSessao");
		Livro = (Livro)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("livroSessao");
		categorias = new Categorias();
	}	
	
		
	public void excluirAvaliacao(Avaliacao u){
		
		avaliacao=u;
		try{
			if(Usuario.getCodUsuario() == avaliacao.getUsuario().getCodUsuario()) {
				avaliacoesPorLivro = buscaListaAL();
				dAO.remove(avaliacao);			
				adicionarMensagem("Excluido com sucesso! ", FacesMessage.SEVERITY_INFO);	
			}else {
				adicionarMensagem("Não é possivel realizar a exclusão", FacesMessage.SEVERITY_INFO);
			}
					
		}catch(Exception e){
			e.printStackTrace();
			adicionarMensagem("Erro na exclus�o ", FacesMessage.SEVERITY_ERROR);
		}
		buscaListaAL();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Usuario> carregaUsuario(){
		try{
			listaUsuario = new ArrayList<Usuario>();
			listaUsuario = (List<Usuario>) dAO.listaTodos(Usuario.class);
		}catch(Exception e){
			e.printStackTrace();		
		}
		return listaUsuario;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Livro> carregaLivro(){
		try{
			listaLivro = new ArrayList<Livro>();
			listaLivro = (List<Livro>) dAO.listaTodos(Livro.class);
		}catch(Exception e){
			e.printStackTrace();		
		}
		return listaLivro;
		
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
	
	public void carregaFormulario(Avaliacao a){
		
		try{
			
			avaliacao = a;
			System.out.println("Entrou no carraga Formulario");
			
		}catch(Exception e){
			e.printStackTrace();
			adicionarMensagem("Erro na Alteração", FacesMessage.SEVERITY_ERROR);
		}
	}
	
	
	public void adicionarMensagem(String mensagem, FacesMessage.Severity tipoErro) {
		FacesMessage fm = new FacesMessage(tipoErro,mensagem,null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Usuario getUsuario() {
		return Usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.Usuario = usuario;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public Livro getLivro() {
		return Livro;
	}

	public void setLivro(Livro livro) {
		this.Livro = livro;
	}

	public List<Livro> getListaLivro() {
		return listaLivro;
	}

	public void setListaLivro(List<Livro> listaLivro) {
		this.listaLivro = listaLivro;
	}

	public List<Avaliacao> getAvaliacoesPorLivro() {
		buscaListaAL();
		return avaliacoesPorLivro;
	}

	public void setAvaliacoesPorLivro(List<Avaliacao> avaliacoesPorLivro) {
		this.avaliacoesPorLivro = avaliacoesPorLivro;
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

	public Armarios getArmarios() {
		return armarios;
	}

	public void setArmarios(Armarios armarios) {
		this.armarios = armarios;
	}
	
	

}
