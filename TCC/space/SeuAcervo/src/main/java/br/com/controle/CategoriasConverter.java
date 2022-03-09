package br.com.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dao.DAO;
import br.com.modelo.Categorias;

@FacesConverter("categoriasConverter")
public class CategoriasConverter implements Converter{

private List<Categorias> categorias = new ArrayList<Categorias>();
	
	@SuppressWarnings("unchecked")
	public CategoriasConverter(){
    	try{
    		DAO dao = new DAO();
    		categorias = (List<Categorias>) dao.listaTodos(Categorias.class);
    	}catch (Exception e) {
			e.printStackTrace();
		}    	
    }
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
    	Optional<Categorias> produto = null;
    	System.out.println("getAsObjectCategorias: " + value);
    	try{        	
	        if (value != null && !value.isEmpty()) {
	    		produto = categorias.stream().filter(p -> 
	    					p.getDescricao().contains(value)).findFirst();
	    	}
        }catch (Exception e) {
			e.printStackTrace();
		}
	    return produto.orElse(new Categorias());
    }

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {  	
    	String str = "";
    	try{
    		str = String.valueOf(((Categorias) value).getDescricao()); 
    		System.out.println("getAsStringCategorias: " + str);
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    	return str;
    }


}
