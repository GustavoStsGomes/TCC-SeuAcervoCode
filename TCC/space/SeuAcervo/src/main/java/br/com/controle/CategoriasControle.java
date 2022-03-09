package br.com.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dao.CategoriasDAO;
import br.com.dao.DAO;
import br.com.modelo.Categorias;
import br.com.modelo.Usuario;

@ViewScoped
@ManagedBean(name="categoriasControle")
public class CategoriasControle {
	
	private Categorias categorias;
	private List<Categorias> listaCategorias = new ArrayList<Categorias>();
	private Usuario Usuario = new Usuario();
	
	private DAO dAO = new DAO();
		
	public CategoriasControle() {
		categorias = new Categorias();
		Usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioSessao");
		buscaCategorias();
	}
	
	public void cancelar() {
		categorias = new Categorias();
	}
	
	public void inserirCategorias(){
		
		try{			
			if(categorias.getCodCategorias() != 0){
				dAO.atualiza(categorias);
				adicionarMensagem("Alteração Realizada", FacesMessage.SEVERITY_INFO);
				
			}else{
				dAO.adiciona(categorias);
				adicionarMensagem("Inserir Realizado com Sucesso", FacesMessage.SEVERITY_INFO);
			}
			//limpar formulario						
		}catch(Exception e){
			e.printStackTrace();
			adicionarMensagem("Erro na inserção ou alteração de dados", FacesMessage.SEVERITY_ERROR);
		}
		categorias = new Categorias();
		buscaCategorias();
	}
		
	public void excluirCategorias(Categorias ed){
		
		categorias = ed;
		CategoriasDAO cDAO = new CategoriasDAO();
		try{
			if(cDAO.verificaExclusaoCat(categorias)==0) {
				listaCategorias = buscaCategorias();
				dAO.remove(categorias);
				adicionarMensagem("Excluido com sucesso! ", FacesMessage.SEVERITY_INFO);
			}else {
				adicionarMensagem("Impossivel excluir, objeto possui ligações", FacesMessage.SEVERITY_INFO);
			}
						
		}catch(Exception e){
			e.printStackTrace();
			adicionarMensagem("Erro na exclusão ", FacesMessage.SEVERITY_ERROR);
		}
		buscaCategorias();
	}	

	@SuppressWarnings("unchecked")
	public List<Categorias> buscaCategorias(){
		try{
			listaCategorias = new ArrayList<Categorias>();
			listaCategorias = (List<Categorias>) dAO.listaTodos(Categorias.class);
		}catch(Exception e){
			e.printStackTrace();		
		}
		return listaCategorias;
		
	}
	
	public void carregaFormulario(Categorias c){
		
		try{
			
			categorias = c;
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

	public Categorias getCategorias() {
		return categorias;
	}

	public void setCategorias(Categorias categorias) {
		this.categorias = categorias;
	}

	public List<Categorias> getListaCategorias() {
		buscaCategorias();
		return listaCategorias;
	}

	public void setListaCategorias(List<Categorias> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}
	
	public Usuario getUsuario() {
		return Usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.Usuario = usuario;
	}


}
