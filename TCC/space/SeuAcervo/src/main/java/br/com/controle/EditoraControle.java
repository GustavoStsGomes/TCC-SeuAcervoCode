package br.com.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dao.DAO;
import br.com.dao.EditoraDAO;
import br.com.modelo.Editora;
import br.com.modelo.Usuario;

@ViewScoped
@ManagedBean(name="editoraControle")
public class EditoraControle {
	
	private Editora editora;
	private List<Editora> listaEditora = new ArrayList<Editora>();
	private Usuario Usuario = new Usuario();
 
	private DAO dAO = new DAO();
		
	public EditoraControle() {
		editora = new Editora();
		Usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioSessao");
		buscaEditoras();
		
	}
	
	public void cancelar() {
		editora = new Editora();
	}
	
	public void inserirEditora(){
		
		//set da string caminho da sessão no objeto antes de adicionar o objeto
		editora.setImagem((String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("imagemSessao"));
		
		try{			
			if(editora.getCodEditora() != 0){
				dAO.atualiza(editora);
				adicionarMensagem("Alteração Realizada", FacesMessage.SEVERITY_INFO);
				
			}else{
				dAO.adiciona(editora);
				adicionarMensagem("Inserir Realizado com Sucesso", FacesMessage.SEVERITY_INFO);
			}
			//limpar formulario						
		}catch(Exception e){
			e.printStackTrace();
			adicionarMensagem("Erro na inserção ou alteração de dados", FacesMessage.SEVERITY_ERROR);
		}
		editora = new Editora();
		Usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioSessao");
		buscaEditoras();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("imagemSessao", "");
	}
		
	public void excluirEditora(Editora ed){
		
		editora = ed;	
		EditoraDAO eDAO = new EditoraDAO();
		try{
			if(eDAO.verificaExclusao(editora)==0) {
				listaEditora = buscaEditoras();
				dAO.remove(editora);			
				adicionarMensagem("Excluído com sucesso! ", FacesMessage.SEVERITY_INFO);	
			}else {
				adicionarMensagem("Impossivel excluir, objeto possui ligações", FacesMessage.SEVERITY_INFO);
			}					
		}catch(Exception e){
			e.printStackTrace();
			adicionarMensagem("Erro na exclusão ", FacesMessage.SEVERITY_ERROR);
		}
		buscaEditoras();
	}	

	@SuppressWarnings("unchecked")
	public List<Editora> buscaEditoras(){
		try{
			listaEditora = new ArrayList<Editora>();
			listaEditora = (List<Editora>) dAO.listaTodos(Editora.class);
		}catch(Exception e){
			e.printStackTrace();		
		}
		return listaEditora;
		
	}
		
	public void carregaFormulario(Editora ed){
		
		try{
			
			editora = ed;
			System.out.println("Entrou no carrega Formulario");
			
		}catch(Exception e){
			e.printStackTrace();
			adicionarMensagem("Erro na Alteração", FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void Ligacao(Editora ed) {				
		try {
			editora = ed;
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("editoraSessao", editora);
			
			FacesContext.getCurrentInstance().getExternalContext().redirect("livrosPorEditora.xhtml");
			
		}catch(Exception e){
			e.printStackTrace();		
		}
			
	}
	
	public void adicionarMensagem(String mensagem, FacesMessage.Severity tipoErro) {
		FacesMessage fm = new FacesMessage(tipoErro,mensagem,null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public List<Editora> getListaEditora() {
		buscaEditoras();
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
	
	

}
