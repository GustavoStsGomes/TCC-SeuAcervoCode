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
import br.com.modelo.Armarios;
import br.com.modelo.Categorias;
import br.com.modelo.Livro;
import br.com.modelo.Usuario;

@ViewScoped
@ManagedBean(name="armariosControle")
public class ArmariosControle {
	
	
	private Armarios armarios;
	private List<Armarios> listaArmarios = new ArrayList<Armarios>();
	private Usuario Usuario = new Usuario();
	private List<Usuario> listaUsuario = new ArrayList<Usuario>();
	private Livro Livro = new Livro();
	private List<Livro> listaLivro = new ArrayList<Livro>();
	private Categorias Categorias = new Categorias();
	private List<Categorias> listaCategorias = new ArrayList<Categorias>();
	
	private DAO dAO = new DAO();
		
	public ArmariosControle() {
		armarios = new Armarios();
		Usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioSessao");
		Livro = new Livro();
		Categorias = new Categorias();
		buscaArmarios();
	}
	
	public void cancelar() {
		armarios = new Armarios();
	}
	
	public void inserirArmarios(){
		
		armarios.setUsuario(Usuario);
		armarios.setLivro(Livro);
		armarios.setCategorias(Categorias);
		
		Date data = new Date(System.currentTimeMillis());  
		SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd"); 	
		System.out.print(formatarDate.format(data));
		armarios.setDataSalvo(data);
		
		ArmariosDAO aDAO = new ArmariosDAO();		
		try{
			if(aDAO.verificaAdicao(Categorias, Usuario, Livro)==0) {
				if(armarios.getCodArmarios() != 0){
					dAO.atualiza(armarios);
					adicionarMensagem("Alteração Realizada", FacesMessage.SEVERITY_INFO);
					
				}else{
					dAO.adiciona(armarios);
					adicionarMensagem("Inserir Realizado com Sucesso", FacesMessage.SEVERITY_INFO);
				}
			}else {
				adicionarMensagem("Não foi possivel incluir, livro ja esta no armario", FacesMessage.SEVERITY_ERROR);
			}						
		}catch(Exception e){
			e.printStackTrace();
			adicionarMensagem("Erro na inserção ou alteração de dados", FacesMessage.SEVERITY_ERROR);
		}
		armarios = new Armarios();
		Usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioSessao");
		Livro = new Livro();
		Categorias = new Categorias();
		buscaArmarios();
	}
		
	public void excluirArmarios(Armarios u){
		
		armarios = u;
		try{
			listaArmarios = buscaArmarios();
			dAO.remove(armarios);
			
			adicionarMensagem("Excluido com sucesso! ", FacesMessage.SEVERITY_INFO);			
		}catch(Exception e){
			e.printStackTrace();
			adicionarMensagem("Erro na exclusão ", FacesMessage.SEVERITY_ERROR);
		}
		buscaArmarios();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Armarios> buscaArmarios(){
		try{
			listaArmarios = new ArrayList<Armarios>();
			listaArmarios = (List<Armarios>) dAO.listaTodos(Armarios.class);
		}catch(Exception e){
			e.printStackTrace();		
		}
		return listaArmarios;
		
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
	
	public void carregaFormulario(Armarios a){
		
		try{
			
			armarios = a;
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

	public Armarios getArmarios() {
		return armarios;
	}

	public void setArmarios(Armarios armarios) {
		this.armarios = armarios;
	}

	public List<Armarios> getListaArmarios() {
		buscaArmarios();
		return listaArmarios;
	}

	public void setListaArmarios(List<Armarios> listaArmarios) {
		this.listaArmarios = listaArmarios;
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

	public Categorias getCategorias() {
		return Categorias;
	}

	public void setCategorias(Categorias categorias) {
		this.Categorias = categorias;
	}

	public List<Categorias> getListaCategorias() {
		return listaCategorias;
	}

	public void setListaCategorias(List<Categorias> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}
}
