package br.com.controle;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.modelo.Usuario;

/**
 * Servlet Filter implementation class FilterControle
 */
@WebFilter(filterName = "/FilterControle")
public class FilterControle implements Filter {

    /**
     * Default constructor. 
     */
    public FilterControle() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response; 

	    Usuario user = (Usuario) req.getSession().getAttribute("usuarioSessao");
	    if(user != null && user.getTipo() != null) {
	    		    
	    	chain.doFilter(request, response);    	
	    }	   	    	
	    else {    	
	    	res.sendRedirect("http://localhost:8080/SeuAcervo/login.xhtml");
		} 			
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
