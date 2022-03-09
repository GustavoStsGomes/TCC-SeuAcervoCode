package br.com.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dao.DAO;
import br.com.modelo.Livro;

@FacesConverter("livroConverter")
public class LivroConverter implements Converter {
	
	private List<Livro> livros = new ArrayList<Livro>();
	
	@SuppressWarnings("unchecked")
	public LivroConverter() {
		try{
    		DAO dao = new DAO();
    		livros = (List<Livro>) dao.listaTodos(Livro.class);
    	}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Optional<Livro> produto = null;
    	System.out.println("getAsObjectLivro: " + value);
    	try{        	
	        if (value != null && !value.isEmpty()) {
	    		produto = livros.stream().filter(p -> 
	    					p.getTitulo().contains(value)).findFirst();
	    	}
        }catch (Exception e) {
			e.printStackTrace();
		}
	    return produto.orElse(new Livro());
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {  	
    	String str = "";
    	try{
    		str = String.valueOf(((Livro) value).getTitulo()); 
    		System.out.println("getAsStringLivro: " + str);
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    	return str;
    }

}
