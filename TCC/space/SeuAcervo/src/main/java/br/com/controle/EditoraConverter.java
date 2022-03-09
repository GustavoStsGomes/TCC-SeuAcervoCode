package br.com.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dao.DAO;
import br.com.modelo.Editora;

@FacesConverter("editoraConverter")
public class EditoraConverter implements Converter{
	
	private List<Editora> editora = new ArrayList<Editora>();
	
	@SuppressWarnings("unchecked")
	public EditoraConverter(){
    	try{
    		DAO dao = new DAO();
    		editora = (List<Editora>) dao.listaTodos(Editora.class);
    	}catch (Exception e) {
			e.printStackTrace();
		}    	
    }
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
    	Optional<Editora> produto = null;
    	System.out.println("getAsObjectEditora: " + value);
    	try{        	
	        if (value != null && !value.isEmpty()) {
	    		produto = editora.stream().filter(p -> 
	    					p.getNome().contains(value)).findFirst();
	    	}
        }catch (Exception e) {
			e.printStackTrace();
		}
	    return produto.orElse(new Editora());
    }

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {  	
    	String str = "";
    	try{
    		str = String.valueOf(((Editora) value).getNome()); 
    		System.out.println("getAsStringEditora: " + str);
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    	return str;
    }

}
