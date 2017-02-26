package co.com.sigpro.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.com.sigpro.bean.Usuario;
import co.com.sigpro.constante.SigproConstante;
import co.com.sigpro.ejb.LoginEJB;
import co.com.sigpro.exception.LogicaException;
import co.com.sigpro.util.Log;



/**
 * Clase para el manejo de login y sesiones del sistema.
 */
@WebServlet(name="LoginServlet" , urlPatterns = {"/page/login"})
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Log logger = new Log();
	
	private final String CLASS_NAME = "[LoginServlet]";
	
	@EJB
	private LoginEJB loginEJB;
		
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

    /**
     * Valida el atributo action de las peticiones y delega la accion a otros metodos.
     * @param HttpServletRequest request la peticion 
     * @param HttpServletResponse response la respuesta
     * */
    private void validarAccion(HttpServletRequest request, HttpServletResponse response){
    	
    	final String METHOD_NAME = "[validarAccion]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
    		String accion = request.getParameter("action");
    		
    		switch (accion) {
				case "iniciarSesion":
					iniciarSesion(request, response);
					break;
				case "cerrarSesion":
					cerrarSesion(request, response);
					break;
				default:
					break;
			}
    		
    	}catch(Exception e){
    		log("Excepcion lanzada. Mensaje:"+e.getMessage());
    	}
    }
    
    /**
     * Iniciar sesion en el sistema.
     * @param HttpServletRequest request la peticion 
     * @param HttpServletResponse response la respuesta 
     * @throws IOException 
     * @throws ServletException 
     * */
    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    	
    	final String METHOD_NAME = "[iniciarSesion]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
    		
    		HttpSession session = request.getSession();
    		
    		String usuario = null;
    		String clave   = null;
    		
    		/** Obtener parametros */
    		usuario = request.getParameter(SigproConstante.CAMPO_USUARIO)!=null? request.getParameter(SigproConstante.CAMPO_USUARIO).trim(): null;
    		clave   = request.getParameter(SigproConstante.CAMPO_CLAVE)!=null? request.getParameter(SigproConstante.CAMPO_CLAVE).trim(): null;
    		
    		
    		/** Validar parametros */
    		if(usuario==null && clave==null){
    			
    			request.setAttribute(SigproConstante.ERROR_MSG, "Se requiere usuario y clave.");
				request.getRequestDispatcher("../login.jsp").forward(request, response);
				
    		}else if(usuario==null){
    			
    			request.setAttribute(SigproConstante.ERROR_MSG, "Se requiere un usuario.");
				request.getRequestDispatcher("../login.jsp").forward(request, response);
    		
    		}else if(clave==null){
    			
    			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Se requiere una clave.");
    			request.setAttribute(SigproConstante.ERROR_MSG, "Se requiere una clave.");
				request.getRequestDispatcher("../login.jsp").forward(request, response);
				
    		}else{
    			
    			Usuario usuarioEntrante = new Usuario();
    			
    			usuarioEntrante.setUsuario(usuario);
    			usuarioEntrante.setClave(clave);
    			
    			Usuario usuarioEncontrado = loginEJB.consultarUsuario(usuarioEntrante);
    			
    			if(usuarioEncontrado==null){
    				request.setAttribute(SigproConstante.ERROR_MSG, "Acceso no autorizado o credenciales incorrectas.");
    				request.getRequestDispatcher("../login.jsp").forward(request, response);
    			}else{
    				
    				request.setAttribute(SigproConstante.NOMBRE_COMPLETO, usuarioEncontrado.getNombre()+" "+usuarioEncontrado.getApellido());
    				request.setAttribute(SigproConstante.NOMBRE_USUARIO,usuario);
    				request.setAttribute(SigproConstante.ROL,usuarioEncontrado.getIdRol());
    				
    				session.setAttribute(SigproConstante.USUARIO_SESSION, usuarioEncontrado);
    				
    				request.getRequestDispatcher("../inicio.jsp").forward(request, response);
    				
    			}
    			
    		}
    	}catch(LogicaException e){
    		log("Se ha lanzado una excepcion en el login. Mensaje:"+e.getMessage());
    		request.setAttribute(SigproConstante.ERROR_MSG, "Acceso no autorizado o credenciales incorrectas.");
			request.getRequestDispatcher("../login.jsp").forward(request, response);    		
    	}catch(Exception e){
    		log("Se ha lanzado una excepcion en el login. Mensaje:"+e.getMessage());
    		request.setAttribute(SigproConstante.ERROR_MSG, "Acceso no autorizado o credenciales incorrectas.");
			request.getRequestDispatcher("../login.jsp").forward(request, response);    		
    	}
    }
    
    /**
     * Cerrar sesion en el sistema.
     * @param HttpServletRequest request la peticion 
     * @param HttpServletResponse response la respuesta 
     * */
    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response){
    	
    	final String METHOD_NAME = "[cerrarSesion]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	HttpSession session = request.getSession();
    	
		try {
			session = request.getSession(false);
			session.setAttribute(SigproConstante.USUARIO_SESSION,null);
			session.invalidate();
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			
		} catch (Exception e) {
			logger.error("Error al cerrar la sesion. Mensaje:"+e.getMessage());
		}
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	final String METHOD_NAME = "[doGet]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
		validarAccion(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	final String METHOD_NAME = "[doPost]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
		validarAccion(request, response);
	}

	
}
