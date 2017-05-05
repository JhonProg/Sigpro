package co.com.sigpro.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.sigpro.bean.Meta;
import co.com.sigpro.bean.Producto;
import co.com.sigpro.bean.ProductoCategoria;
import co.com.sigpro.bean.TipoDocumento;
import co.com.sigpro.bean.Usuario;
import co.com.sigpro.constante.RolConstante;
import co.com.sigpro.ejb.MetaEJB;
import co.com.sigpro.ejb.UsuarioEJB;
import co.com.sigpro.exception.LogicaException;
import co.com.sigpro.util.Log;

/**
 * Servlet implementation class MetaServlet
 */
@WebServlet(name="MetaServlet" , urlPatterns = {"/page/meta"})
public class MetaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Log logger = new Log();	
	private final String CLASS_NAME = "[ProductoServlet]";
	
	@EJB
	private UsuarioEJB usuarioEJB;
	
	@EJB
	private MetaEJB metaEJB;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MetaServlet() {
        super();
    }

    private void validarAccion(HttpServletRequest request, HttpServletResponse response){
    	
    	final String METHOD_NAME = "[validarAccion]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
    		String accion = request.getParameter("action");
    		
    		logger.info("[action:"+accion+"]");    		
    		
    		switch (accion) {
				case "cargarInicioMeta":
					cargarInicioMeta(request, response);
					break;
				case "crearCargarNuevaMeta":
					crearCargarNuevaMeta(request, response);
					break;
				case "insertarMeta":
					insertarMeta(request, response);
					break;
				default:
					break;
			}
    		
    	}catch(Exception e){
    		log("Excepcion lanzada. Mensaje:"+e.getMessage());
    	}
    }
    
    private void cargarInicioMeta(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	final String METHOD_NAME = "[cargarInicioMeta]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
    		
    		request.getRequestDispatcher("../pages/director/metas.jsp").forward(request, response);
    		
    	}catch(Exception e){
    		response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, e.getMessage());
    	}
    }
    
    
    
    private void crearCargarNuevaMeta(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	final String METHOD_NAME = "[crearCargarNuevaMeta]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
    		List<Usuario> promotores = usuarioEJB.consultarUsuarioPorRol(RolConstante.PROMOTOR);
    		request.setAttribute("promotores", promotores);
    		logger.info("hola");
    		request.getRequestDispatcher("../pages/director/nuevaMeta.jsp").forward(request, response);

    	}catch(Exception e){
    		response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, e.getMessage());
    	}
    }
    
    
    private void insertarMeta(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	final String METHOD_NAME = "[insertarMeta]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
    		response.setContentType("text/plain");
    		PrintWriter salida = response.getWriter();
    		    		
    		Integer idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
    		Integer idMes    = Integer.parseInt(request.getParameter("idMes"));
    		Integer valorMeta           = Integer.parseInt(request.getParameter("meta"));
    		    		    		
    		Meta meta =  new Meta();
    		meta.setIdUsuario(idUsuario);
    		meta.setIdMes(idMes);
    		meta.setMeta(valorMeta);
    		
    		int resultadoInsercion = metaEJB.insertarMeta(meta);
    		
			if (resultadoInsercion>0) {
				
				salida.println(""+resultadoInsercion);
				
			} else {
				throw new LogicaException("Error al insertar la meta. Intente mas tarde.");
			}
    		
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, e.getMessage());
    	}
    	
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		validarAccion(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		validarAccion(request, response);
	}

}
