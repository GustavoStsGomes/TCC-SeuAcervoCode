package br.com.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dao.DAO;
import br.com.modelo.Estado;
import br.com.modelo.Usuario;

@ViewScoped
@ManagedBean(name="estadoControle")
public class EstadoControle {
	
	private Estado estado = new Estado();
	private List<Estado> listaEstado = new ArrayList<Estado>();
	private Usuario Usuario = new Usuario();
	
	private DAO dAO = new DAO();
	
	public EstadoControle() {
		estado = new Estado();
		Usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioSessao");
		buscaEstado();
	}
	
	public void cancelar() {
		estado = new Estado();
	}
	
	public void inserirEstado(){
		
		try{			
			if(estado.getSigla() != null){
				dAO.atualiza(estado);
				adicionarMensagem("Alteração Realizada", FacesMessage.SEVERITY_INFO);
				
			}else{
				dAO.adiciona(estado);
				adicionarMensagem("Inserir Realizado com Sucesso", FacesMessage.SEVERITY_INFO);
			}
			//limpar formulario						
		}catch(Exception e){
			e.printStackTrace();
			adicionarMensagem("Erro na inserção ou alteração de dados", FacesMessage.SEVERITY_ERROR);
		}
		estado = new Estado();
		Usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioSessao");
		buscaEstado();
	}
		
	public void excluirEstado(Estado ed){
		estado=ed;
		try{
			listaEstado = buscaEstado();
			dAO.remove(estado);
			
			adicionarMensagem("Exclu�do com sucesso! ", FacesMessage.SEVERITY_INFO);			
		}catch(Exception e){
			e.printStackTrace();
			adicionarMensagem("Erro na exclus�o ", FacesMessage.SEVERITY_ERROR);
		}
		buscaEstado();
	}	

	@SuppressWarnings("unchecked")
	public List<Estado> buscaEstado(){
		try{
			listaEstado = new ArrayList<Estado>();
			listaEstado = (List<Estado>) dAO.listaTodos(Estado.class);
		}catch(Exception e){
			e.printStackTrace();		
		}
		return listaEstado;
		
	}
	
	public void carregaFormulario(Estado es){
		
		try{
			
			estado = es;
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getListaEstado() {
		buscaEstado();
		return listaEstado;
	}

	public void setListaEstado(List<Estado> listaEstado) {
		this.listaEstado = listaEstado;
	}
	
	public Usuario getUsuario() {
		return Usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.Usuario = usuario;
	}

}