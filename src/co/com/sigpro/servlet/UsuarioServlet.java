package co.com.sigpro.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.sigpro.bean.Rol;
import co.com.sigpro.bean.TipoDocumento;
import co.com.sigpro.bean.Usuario;
import co.com.sigpro.constante.EstadoEnum;
import co.com.sigpro.ejb.CatalogoEJB;
import co.com.sigpro.ejb.UsuarioEJB;
import co.com.sigpro.exception.LogicaException;
import co.com.sigpro.util.Log;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet(name="UsuarioServlet" , urlPatterns = {"/page/usuario"})
public class UsuarioServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Log logger = new Log();	
	private final String CLASS_NAME = "[UsuarioServlet]";
	
	@EJB
	private CatalogoEJB catalogoEJB;
	
	@EJB
	private UsuarioEJB usuarioEJB;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServlet() {
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
    		
    		logger.info("[action:"+accion+"]");    		
    		
    		switch (accion) {
				case "cargarConsultarUsuario":
					cargarConsultarUsuario(request, response);
					break;
				case "consultarUsuarios":
					consultarUsuarios(request, response);
					break;
				case "crearCargarNuevoUsuario":
					crearCargarNuevoUsuario(request, response);
					break;
				case "insertarUsuario":
					insertarUsuario(request, response);
					break;
				case "cargarEditarUsuario":
					cargarEditarUsuario(request, response);
					break;
				case "editarUsuario":
					editarUsuario(request, response);
					break;
				default:
					break;
			}
    		
    	}catch(Exception e){
    		log("Excepcion lanzada. Mensaje:"+e.getMessage());
    	}
    }
    
    private void cargarConsultarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	final String METHOD_NAME = "[cargarConsultarUsuario]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
		
    		List<TipoDocumento> listaTiposDocumento = catalogoEJB.consultarTiposDocumento();
    		request.setAttribute("listaTiposDocumento", listaTiposDocumento);
			request.getRequestDispatcher("../pages/admin/consultaUsuario.jsp").forward(request, response);
			
    	}catch(Exception e){
    		response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, e.getMessage());
    	}
    }
    
    private void consultarUsuarios(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	final String METHOD_NAME = "[consultarUsuarios]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
		
    		List<Usuario> usuarios = new ArrayList<>();
    		
    		Integer tipoBusquedaUsuario = Integer.parseInt(request.getParameter("tipoBusquedaUsuario"));
    		
    		switch(tipoBusquedaUsuario){
    			case 1:
    				String nombre = request.getParameter("nombre");
    				usuarios      = usuarioEJB.consultarUsuariosPorNombre(nombre);
    				break;
    			case 2:
    				Integer tipoDocumento    = Integer.parseInt(request.getParameter("tipoDocumento"));
    				String numIdentificacion = request.getParameter("numIdentificacion");
    				usuarios                 = usuarioEJB.consultarUsuariosPorTipoNumeroIdentificacion(tipoDocumento, numIdentificacion);
        			break;
    		}
    		
    		request.setAttribute("usuarios", usuarios);
			request.getRequestDispatcher("../pages/admin/listaUsuarios.jsp").forward(request, response);
			
    	}catch(Exception e){
    		response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, e.getMessage());
    	}
    }
    
    
    private void crearCargarNuevoUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	final String METHOD_NAME = "[crearCargarNuevoUsuario]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
		
    		List<TipoDocumento> listaTiposDocumento = catalogoEJB.consultarTiposDocumento();
    		List<Rol> listaRoles                    = catalogoEJB.consultarRoles();
    		
    		request.setAttribute("listaTiposDocumento", listaTiposDocumento);
    		request.setAttribute("listaRoles", listaRoles);
    		
    		request.getRequestDispatcher("../pages/admin/nuevoUsuario.jsp").forward(request, response);
			
    	}catch(Exception e){
    		response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, e.getMessage());
    	}
    }
    
    private void insertarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	final String METHOD_NAME = "[insertarUsuario]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
    		response.setContentType("text/plain");
    		PrintWriter salida = response.getWriter();
    		    		
    		Integer idTipoDocumento  = Integer.parseInt(request.getParameter("idTipoDocumento"));
    		String numIdentificacion = request.getParameter("numIdentificacion");
    		Integer idRol            = Integer.parseInt(request.getParameter("idRol"));
    		String nombres           = request.getParameter("nombres");
    		String apellidos         = request.getParameter("apellidos");
    		String usuario           = request.getParameter("usuario");
    		String clave             = request.getParameter("clave");
    		
    		Usuario nuevoUsuario = new Usuario();
    		
    		nuevoUsuario.setIdTipoDocumento(idTipoDocumento);
    		nuevoUsuario.setIdRol(idRol);
    		nuevoUsuario.setNumeroDocumento(numIdentificacion);
    		nuevoUsuario.setUsuario(usuario);
    		nuevoUsuario.setClave(clave);
    		nuevoUsuario.setNombre(nombres);
    		nuevoUsuario.setApellido(apellidos);
    		nuevoUsuario.setEstado(EstadoEnum.ACTIVO.getIndex());
    		
    		int resultadoInsercion = usuarioEJB.insertarUsuario(nuevoUsuario);
    		
			if (resultadoInsercion>0) {
				
				salida.println(""+resultadoInsercion);
				
			} else {
				throw new LogicaException("Error al registrar los datos del nuevo usuario.");
			}
    		
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, e.getMessage());
    	}
    	
    }
    
    private void cargarEditarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	final String METHOD_NAME = "[crearCargarNuevoUsuario]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
		
    		Integer idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
    		
    		logger.info("idUsuario : ["+idUsuario+"]");
    		
    		Usuario usuarioEditar = usuarioEJB.consultarUsuarioPorIdUsuario(idUsuario);
    		
    		List<TipoDocumento> listaTiposDocumento = catalogoEJB.consultarTiposDocumento();
    		List<Rol> listaRoles                    = catalogoEJB.consultarRoles();
    		
    		request.setAttribute("listaTiposDocumento", listaTiposDocumento);
    		request.setAttribute("listaRoles", listaRoles);
    		request.setAttribute("usuario", usuarioEditar);
    		
    		logger.info("antes del dispatcher..");
    		
    		request.getRequestDispatcher("../pages/admin/editarUsuario.jsp").forward(request, response);
			
    	}catch(Exception e){
    		response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, e.getMessage());
    	}
    }
    
    private void editarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	final String METHOD_NAME = "[insertarUsuario]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
    		response.setContentType("text/plain");
    		PrintWriter salida = response.getWriter();
    		
    		Integer idUsuarioEditando = Integer.parseInt(request.getParameter("idUsuarioEditando"));
    		Integer idTipoDocumento   = Integer.parseInt(request.getParameter("idTipoDocumento"));
    		String numIdentificacion  = request.getParameter("numIdentificacion");
    		Integer idRol             = Integer.parseInt(request.getParameter("idRol"));
    		String nombres            = request.getParameter("nombres");
    		String apellidos          = request.getParameter("apellidos");
    		String usuario            = request.getParameter("usuario");
    		String clave              = request.getParameter("clave");
    		Integer estado            = Integer.parseInt(request.getParameter("idEstado"));
    		
    		Usuario usuarioEdicion = new Usuario();
    		
    		usuarioEdicion.setIdUsuario(idUsuarioEditando);
    		usuarioEdicion.setIdTipoDocumento(idTipoDocumento);
    		usuarioEdicion.setIdRol(idRol);
    		usuarioEdicion.setNumeroDocumento(numIdentificacion);
    		usuarioEdicion.setUsuario(usuario);
    		usuarioEdicion.setClave(clave);
    		usuarioEdicion.setNombre(nombres);
    		usuarioEdicion.setApellido(apellidos);
    		usuarioEdicion.setEstado(estado);
    		
    		int resultado = usuarioEJB.editarUsuario(usuarioEdicion);
    		
			if (resultado>0) {
				
				salida.println(""+resultado);
				
			} else {
				throw new LogicaException("Error al editar los datos del usuario.");
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
