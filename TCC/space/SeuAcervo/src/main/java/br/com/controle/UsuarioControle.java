package br.com.controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dao.DAO;
import br.com.dao.UsuarioDAO;
import br.com.modelo.Cidade;
import br.com.modelo.Usuario;

@ViewScoped
@ManagedBean(name="usuarioControle")
public class UsuarioControle {
	
	private Usuario usuario;
	private List<Usuario> listaUsuario = new ArrayList<Usuario>();
	private List<Usuario> listaSemAdmin = new ArrayList<Usuario>();
	private Cidade Cidade;
	private List<Cidade> listaCidade = new ArrayList<Cidade>();
	private Usuario UsuarioLogado = new Usuario();
 
	private DAO dAO = new DAO();
		
	public UsuarioControle() {
		usuario = new Usuario();
		Cidade = new Cidade();
		UsuarioLogado = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioSessao");
		buscaUsuario();
		buscaSemAdmin();
	}

	public void cancelar() {
		usuario = new Usuario();
		Cidade = new Cidade();
	}
	
	public void inserirUsuario(){
		
		usuario.setCidade(Cidade);
		usuario.setPerfil((String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("imagemSessao"));
		
		try{			
			if(usuario.getCodUsuario() != 0){
				dAO.atualiza(usuario);
				adicionarMensagem("Alteração Realizada", FacesMessage.SEVERITY_INFO);
				
			}else{
				dAO.adiciona(usuario);
				adicionarMensagem("Inserir Realizado com Sucesso", FacesMessage.SEVERITY_INFO);
				
			}
			//limpar formulario						
		}catch(Exception e){
			e.printStackTrace();
			adicionarMensagem("Erro na inserção ou alteração de dados", FacesMessage.SEVERITY_ERROR);
		}
		usuario = new Usuario();
		Cidade = new Cidade();
		UsuarioLogado = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioSessao");
		buscaUsuario();
	}
		
	public void excluirUsuario(Usuario c){
		
		usuario =c;
		UsuarioDAO uDao = new UsuarioDAO();
		try{
			if(uDao.verificaExclusaoAR(usuario)==0 && uDao.verificaExclusaoAV(usuario)==0) {
				listaUsuario = buscaUsuario();
				dAO.remove(usuario);
				adicionarMensagem("Excluido com sucesso! ", FacesMessage.SEVERITY_INFO);	
			}else {
				adicionarMensagem("Impossivel excluir, objeto possui ligações", FacesMessage.SEVERITY_ERROR);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			adicionarMensagem("Erro na exclus�o ", FacesMessage.SEVERITY_ERROR);
		}
		buscaUsuario();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Usuario> buscaUsuario(){
		try{
			listaUsuario = new ArrayList<Usuario>();
			listaUsuario = (List<Usuario>) dAO.listaTodos(Usuario.class);
		}catch(Exception e){
			e.printStackTrace();		
		}
		return listaUsuario;		
	}
	
	private List<Usuario> buscaSemAdmin() {
		try{
			UsuarioDAO uDAO = new UsuarioDAO();
			listaSemAdmin = new ArrayList<Usuario>();
			listaSemAdmin = uDAO.listaDeUsuario(Usuario.class);
		}catch(Exception e){
			e.printStackTrace();		
		}
		return listaSemAdmin;
		
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
	
	public void carregaFormulario(Usuario u){
		
		try{
			
			usuario = u;
			System.out.println("Entrou no carraga Formulario");
			
		}catch(Exception e){
			e.printStackTrace();
			adicionarMensagem("Erro na Alteração", FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void logar() throws IOException{
		try {
			UsuarioDAO usuarioDao = new UsuarioDAO();
			usuario = usuarioDao.logarHibernate(usuario.getEmail(), usuario.getSenha());
			
			if(usuario.getCodUsuario() != 0) {
				if(usuario.getTipo().equals("admin")) {
					
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioSessao", usuario);					
					FacesContext.getCurrentInstance().getExternalContext().redirect("admin/livro.xhtml");
					
				}else {
					
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioSessao", usuario);				
					FacesContext.getCurrentInstance().getExternalContext().redirect("ListaLivros.xhtml");
					
				}
			}else {
				adicionarMensagem("Login incorreta", FacesMessage.SEVERITY_WARN);										
				
			}
		}catch(Throwable t) {
			t.printStackTrace();
			adicionarMensagem("Erro do login! ", FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void logout(){
		try{
			UsuarioLogado = new Usuario();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioSessao", null);
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");		
		}catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void novoUsuario(){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("novoUsuario.xhtml");
			
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	public void outroPerfil(Usuario us) {				
		try {		
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("perfilSessao", us);
			System.out.println("++++++++++++++++++++++++++++++++++++ TESTE " + us.getNome());
			FacesContext.getCurrentInstance().getExternalContext().redirect("OutroPerfil.xhtml");			
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

	public List<Usuario> getListaUsuario() {
		buscaUsuario();
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public Cidade getCidade() {
		return Cidade;
	}

	public void setCidade(Cidade cidade) {
		this.Cidade = cidade;
	}

	public List<Cidade> getListaCidade() {
		return listaCidade;
	}

	public void setListaCidade(List<Cidade> listaCidade) {
		this.listaCidade = listaCidade;
	}

	public Usuario getUsuarioLogado() {
		return UsuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		UsuarioLogado = usuarioLogado;
	}

	public List<Usuario> getListaSemAdmin() {
		return listaSemAdmin;
	}

	public void setListaSemAdmin(List<Usuario> listaSemAdmin) {
		this.listaSemAdmin = listaSemAdmin;
	}
	
}
