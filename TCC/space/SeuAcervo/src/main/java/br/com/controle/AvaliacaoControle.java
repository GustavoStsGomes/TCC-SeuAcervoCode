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
import br.com.modelo.Avaliacao;
import br.com.modelo.Livro;
import br.com.modelo.Usuario;

@ViewScoped
@ManagedBean(name="avaliacaoControle")
public class AvaliacaoControle {
	
	private Avaliacao avaliacao;
	private List<Avaliacao> listaAvaliacao = new ArrayList<Avaliacao>();
	private Usuario Usuario = new Usuario();
	private List<Usuario> listaUsuario = new ArrayList<Usuario>();
	private Livro Livro;
	private List<Livro> listaLivro = new ArrayList<Livro>();
	
	private DAO dAO = new DAO();
		
	public AvaliacaoControle() {
		avaliacao = new Avaliacao();
		Usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioSessao");
		Livro = new Livro();
		buscaAvaliacao();
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
		Usuario = new Usuario();
		Livro = new Livro();
		Usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioSessao");
		buscaAvaliacao();
	}
		
	public void excluirAvaliacao(Avaliacao u){
		avaliacao = u;
		try{
			listaAvaliacao = buscaAvaliacao();

			dAO.remove(avaliacao);
			
			adicionarMensagem("Excluido com sucesso! ", FacesMessage.SEVERITY_INFO);			
		}catch(Exception e){
			e.printStackTrace();
			adicionarMensagem("Erro na exclusao ", FacesMessage.SEVERITY_ERROR);
		}
		buscaAvaliacao();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Avaliacao> buscaAvaliacao(){
		try{
			listaAvaliacao = new ArrayList<Avaliacao>();
			listaAvaliacao = (List<Avaliacao>) dAO.listaTodos(Avaliacao.class);
		}catch(Exception e){
			e.printStackTrace();		
		}
		return listaAvaliacao;
		
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

	public List<Avaliacao> getListaAvaliacao() {
		buscaAvaliacao();
		return listaAvaliacao;
	}

	public void setListaAvaliacao(List<Avaliacao> listaAvaliacao) {
		this.listaAvaliacao = listaAvaliacao;
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

	
}
