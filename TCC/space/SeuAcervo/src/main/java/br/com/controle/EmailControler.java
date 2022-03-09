package br.com.controle;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.modelo.Usuario;
import br.com.util.Email;

@ManagedBean(name = "emailController")
public class EmailControler {
	
	private String email;
	private String assunto;
	private String mensagem;
	private Usuario perfil;
	
	public EmailControler() {
		perfil = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("perfilSessao");
	}
	
	public void enviarEmail() {		
		
		email = perfil.getEmail();
		
		try {
			Email.enviarMail(email, assunto, mensagem);
			email = "";
			assunto = "";
			mensagem = "";
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
