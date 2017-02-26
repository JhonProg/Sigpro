package co.com.sigpro.security;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.com.sigpro.bean.Usuario;
import co.com.sigpro.constante.SigproConstante;
import co.com.sigpro.ejb.LoginEJB;
import co.com.sigpro.util.Log;

@WebFilter(filterName = "LoginFilter", urlPatterns = { "/page/*" })
public class LoginFilter implements Filter {

	private Log logger = new Log();
	private final String CLASS_NAME = "[LoginFilter]";
	
	@EJB
	LoginEJB loginEJB;
	
	@Override
	public void destroy() {
		final String METHOD_NAME = "[destroy]";
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
	}

	@SuppressWarnings("unused")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		final String METHOD_NAME = "[doFilter]";
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		String aplicacion = (String) req.getServletContext().getAttribute(SigproConstante.APLICACION);
		
		try {

			if (session != null && !session.isNew()) {
				Usuario user = (Usuario) session.getAttribute(SigproConstante.USUARIO_SESSION);
				
				if(user==null){
					final String u = req.getParameter(SigproConstante.CAMPO_USUARIO);
					final String c = req.getParameter(SigproConstante.CAMPO_CLAVE);
					
					if (null != u && null != c) {
						
						Usuario us = new Usuario();
						
						us.setUsuario(u);
						us.setClave(c);
						
						Usuario usuario = loginEJB.consultarUsuario(us);
						
						if (null != usuario) {
							
							session.setAttribute(SigproConstante.USUARIO_SESSION, usuario);
												
						} else {
							throw new Exception("[" + u + "] Usuario y/o clave incorrecta");
						}
					} else {
						throw new Exception("Nueva session");
					}
				}else{
					logger.info("usuario ya se encuentra logueado en la aplicacion.");
				}
				chain.doFilter(request, response);
			}else{
				throw new Exception("Su sesion caduco, por favor vuelva a ingresar.");
			}
				
		}catch(Exception e){
			logger.error(e.getMessage());
						
			req.setAttribute(SigproConstante.ERROR_TITLE, "error se de sesion");
			req.setAttribute(SigproConstante.ERROR_MSG, e.getMessage());
			req.getRequestDispatcher("../login.jsp").forward(req, resp);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	
}
