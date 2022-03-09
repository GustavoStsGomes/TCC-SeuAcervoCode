package br.com.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dao.DAO;
import br.com.modelo.Usuario;

@FacesConverter("usuarioConverter")
public class UsuarioConverter implements Converter {
	
private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	@SuppressWarnings("unchecked")
	public UsuarioConverter(){
    	try{
    		DAO dao = new DAO();
    		usuarios = (List<Usuario>) dao.listaTodos(Usuario.class);
    	}catch (Exception e) {
			e.printStackTrace();
		}    	
    }
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
    	Optional<Usuario> produto = null;
    	System.out.println("getAsObjectUsuario: " + value);
    	try{        	
	        if (value != null && !value.isEmpty()) {
	    		produto = usuarios.stream().filter(p -> 
	    					p.getNome().contains(value)).findFirst();
	    	}
        }catch (Exception e) {
			e.printStackTrace();
		}
	    return produto.orElse(new Usuario());
    }

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {  	
    	String str = "";
    	try{
    		str = String.valueOf(((Usuario) value).getNome()); 
    		System.out.println("getAsStringUsuario: " + str);
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    	return str;
    }

}
