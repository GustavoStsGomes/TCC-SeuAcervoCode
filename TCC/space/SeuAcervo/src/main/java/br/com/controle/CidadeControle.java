package br.com.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dao.DAO;
import br.com.modelo.Cidade;
import br.com.modelo.Estado;
import br.com.modelo.Usuario;

@ViewScoped
@ManagedBean(name="cidadeControle")
public class CidadeControle {
	
	private Cidade cidade;
	private List<Cidade> listaCidade = new ArrayList<Cidade>();
	private Estado Estado;
	private List<Estado> listaEstado = new ArrayList<Estado>();
	private Usuario Usuario = new Usuario();
	 
	private DAO dAO = new DAO();
		
	public CidadeControle() {
		cidade = new Cidade();
		Estado = new Estado();
		Usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioSessao");
		buscaCidade();
	}
	
	public void cancelar() {
		cidade = new Cidade();
		Estado = new Estado();
	}
	
	public void inserirCidade(){
		
		cidade.setEstado(Estado);
		
		try{			
			if(cidade.getCodCidade() != 0){
				dAO.atualiza(cidade);
				adicionarMensagem("Alteração Realizada", FacesMessage.SEVERITY_INFO);
				
			}else{
				dAO.adiciona(cidade);
				adicionarMensagem("Inserir Realizado com Sucesso", FacesMessage.SEVERITY_INFO);
			}
			//limpar formulario						
		}catch(Exception e){
			e.printStackTrace();
			adicionarMensagem("Erro na inserção ou alteração de dados", FacesMessage.SEVERITY_ERROR);
		}
		cidade = new Cidade();
		Estado = new Estado();
		buscaCidade();
	}
		
	public void excluirCidade(Cidade c){
		
		cidade = c;
		try{
			listaCidade = buscaCidade();
			dAO.remove(cidade);
			
			adicionarMensagem("Exclu�do com sucesso! ", FacesMessage.SEVERITY_INFO);			
		}catch(Exception e){
			e.printStackTrace();
			adicionarMensagem("Erro na exclus�o ", FacesMessage.SEVERITY_ERROR);
		}
		buscaCidade();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Cidade> buscaCidade(){
		try{
			listaCidade = new ArrayList<Cidade>();
			listaCidade = (List<Cidade>) dAO.listaTodos(Cidade.class);
		}catch(Exception e){
			e.printStackTrace();		
		}
		return listaCidade;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Estado> carregaEstado(){
		try{
			listaEstado = new ArrayList<Estado>();
			listaEstado = (List<Estado>) dAO.listaTodos(Estado.class);
		}catch(Exception e){
			e.printStackTrace();		
		}
		return listaEstado;
		
	}
	
	public void carregaFormulario(Cidade c){
		
		try{
			
			cidade = c;
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

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getListaCidade() {
		buscaCidade();
		return listaCidade;
	}

	public void setListaCidade(List<Cidade> listaCidade) {
		this.listaCidade = listaCidade;
	}

	public Estado getEstado() {
		return Estado;
	}

	public void setEstado(Estado estado) {
		this.Estado = estado;
	}

	public List<Estado> getListaEstado() {
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
