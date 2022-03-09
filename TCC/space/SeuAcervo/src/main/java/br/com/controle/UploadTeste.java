package br.com.controle;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

@ViewScoped
@ManagedBean(name="UploadTeste")
public class UploadTeste {
	
	public void upArquivo(FileUploadEvent event) throws FileNotFoundException, IOException {  
		
		adicionarMensagem("Sucesso " + event.getFile().getFileName() + " foi carregado." , FacesMessage.SEVERITY_INFO);	
		
        String nomeArquivo = event.getFile().getFileName();      
        
        //Para Windows
        //String a = FacesContext.getCurrentInstance().getExternalContext().getRealPath(""+"\\imagens\\"+event.getFile().getFileName());         
        
        //Para Linux
        String a = FacesContext.getCurrentInstance().getExternalContext().getRealPath(""+"//imagens//"+event.getFile().getFileName());
        
        System.out.println("ENDERECO: " + a);
        
        byte[] conteudo = event.getFile().getContents();  
        FileOutputStream fos = new FileOutputStream(a);  
        fos.write(conteudo);  
        fos.close();

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("imagemSessao", nomeArquivo);
        
    }
		
	public void adicionarMensagem(String mensagem, FacesMessage.Severity tipoErro) {
		FacesMessage fm = new FacesMessage(tipoErro,mensagem,null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}
	
	/*System.out.println("Caminho: " + c);        
    String a = c.replace("\\SeuAcervo-0.0.1-SNAPSHOT.war\\", "");        
    System.out.println("REP: " + a);*/
}
