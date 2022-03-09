package br.com.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dao.DAO;
import br.com.modelo.Estado;

@FacesConverter("estadoConverter")
public class EstadoConverter implements Converter{

	private List<Estado> estados = new ArrayList<Estado>();
	
	@SuppressWarnings("unchecked")
	public EstadoConverter(){
    	try{
    		DAO dao = new DAO();
	    	estados = (List<Estado>) dao.listaTodos(Estado.class);
    	}catch (Exception e) {
			e.printStackTrace();
		}    	
    }
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
    	Optional<Estado> produto = null;
    	System.out.println("getAsObjectEstado: " + value);
    	try{        	
	        if (value != null && !value.isEmpty()) {
	    		produto = estados.stream().filter(p -> 
	    					p.getSigla().contains(value)).findFirst();
	    	}
        }catch (Exception e) {
			e.printStackTrace();
		}
	    return produto.orElse(new Estado());
    }

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {  	
    	String str = "";
    	try{
    		str = String.valueOf(((Estado) value).getSigla()); 
    		System.out.println("getAsStringEstado: " + str);
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    	return str;
    }

}
