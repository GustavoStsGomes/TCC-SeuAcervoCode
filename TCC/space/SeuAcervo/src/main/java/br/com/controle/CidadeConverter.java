package br.com.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dao.DAO;
import br.com.modelo.Cidade;

@FacesConverter("cidadeConverter")
public class CidadeConverter implements Converter{
	
	private List<Cidade> cidade = new ArrayList<Cidade>();
	
	@SuppressWarnings("unchecked")
	public CidadeConverter(){
    	try{
    		DAO dao = new DAO();
	    	cidade = (List<Cidade>) dao.listaTodos(Cidade.class);
    	}catch (Exception e) {
			e.printStackTrace();
		}    	
    }
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
    	Optional<Cidade> produto = null;
    	System.out.println("getAsObjectCidade: " + value);
    	try{        	
	        if (value != null && !value.isEmpty()) {
	    		produto = cidade.stream().filter(p -> 
	    					p.getNome().contains(value)).findFirst();
	    	}
        }catch (Exception e) {
			e.printStackTrace();
		}
	    return produto.orElse(new Cidade());
    }

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {  	
    	String str = "";
    	try{
    		str = String.valueOf(((Cidade) value).getNome()); 
    		System.out.println("getAsStringCidade: " + str);
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    	return str;
    }
}
