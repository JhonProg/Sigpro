package co.com.sigpro.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.sigpro.bean.Cliente;
import co.com.sigpro.constante.EstadoEnum;
import co.com.sigpro.ejb.ClienteEJB;
import co.com.sigpro.util.Log;

/**
 * Servlet implementation class ClienteServlet
 */
@WebServlet(name="ClienteServlet" , urlPatterns = {"/page/cliente"})
public class ClienteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;    
	private Log logger = new Log();	
	private final String CLASS_NAME = "[ClienteServlet]";
	
	@EJB
	private ClienteEJB clienteEJB;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClienteServlet() {
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
				case "validarSiExisteCliente":
					validarSiExisteCliente(request, response);
					break;
				case "editarCliente":
					editarCliente(request, response);
				case "crearCliente":
					crearCliente(request,response);
				default:
					break;
			}
    		
    	}catch(Exception e){
    		log("Excepcion lanzada. Mensaje:"+e.getMessage());
    	}
    }
    
    private void validarSiExisteCliente(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	final String METHOD_NAME = "[validarSiExisteCliente]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
    		
    		response.setContentType("text/plain");
    		PrintWriter salida = response.getWriter();
    		
    		Integer idTipoDocumentoCliente  = Integer.parseInt(request.getParameter("idTipoDocumentoCliente"));
    		String numIdentificacionCliente = request.getParameter("numIdentificacionCliente");
    		
    		Cliente clienteEncontrado = clienteEJB.validaClientePorTipoNumeroDocumento(idTipoDocumentoCliente, numIdentificacionCliente);
    		
    		if(clienteEncontrado!=null){
    			
    			int idcliente          = clienteEncontrado.getIdcliente();
        		int idTipoDocumento    = clienteEncontrado.getIdTipoDocumento();
        		String numeroDocumento = clienteEncontrado.getNumeroDocumento();
        		String nombre          = clienteEncontrado.getNombre();
        		String apellido        = clienteEncontrado.getApellido();
        		String direccion       = clienteEncontrado.getDireccion();
        		String telefono        = clienteEncontrado.getTelefono();
        		int estado             = clienteEncontrado.getEstado();
    			
    			String datos = idcliente+"|"+idTipoDocumento+"|"+numeroDocumento+"|"+nombre+"|"+apellido+"|"+direccion+"|"+telefono+"|"+estado;
    			salida.println(datos);
    			
    		}else{
    			throw new Exception("El cliente no existe. Debe regsitrarlo.");
    		}
    		    		
    	}catch(Exception e){
    		response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, e.getMessage());
    	}
    }
    
    
    /**
     *  
     * */
    private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	final String METHOD_NAME = "[editarCliente]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
    		
    		response.setContentType("text/plain");
    		PrintWriter salida = response.getWriter();
    		
    		int idClienteActualizar                           = Integer.parseInt(request.getParameter("idClienteEncontrado"));
    		int idTipoIdentificacionClienteEncontrado         = Integer.parseInt(request.getParameter("idTipoIdentificacionClienteEncontrado"));
    		String numIdentificacionClienteEncontrado         = request.getParameter("numIdentificacionClienteEncontrado");
    		//String numIdentificacionClienteRepetidoEncontrado = request.getParameter("numIdentificacionClienteRepetidoEncontrado");
    		String nombresClienteEncontrado                   = request.getParameter("nombresClienteEncontrado");
    		String apellidosClienteEncontrado                 = request.getParameter("apellidosClienteEncontrado");
    		String direccionClienteEncontrado                 = request.getParameter("direccionClienteEncontrado");
    		String telefonoClienteEncontrado                  = request.getParameter("telefonoClienteEncontrado");
    		
    		logger.info("idClienteActualizar = "+idClienteActualizar);
    		
    		Cliente clienteActualizar = new Cliente();
    		
    		clienteActualizar.setIdcliente(idClienteActualizar);
    		clienteActualizar.setIdTipoDocumento(idTipoIdentificacionClienteEncontrado);
    		clienteActualizar.setNumeroDocumento(numIdentificacionClienteEncontrado);
    		clienteActualizar.setNombre(nombresClienteEncontrado);
    		clienteActualizar.setApellido(apellidosClienteEncontrado);
    		clienteActualizar.setDireccion(direccionClienteEncontrado);
    		clienteActualizar.setTelefono(telefonoClienteEncontrado);
    		
    		int resultadoActualizacion = clienteEJB.actualizarCliente(clienteActualizar);
    		
    		if(resultadoActualizacion>0){
    			String datos = idClienteActualizar+"|"+idTipoIdentificacionClienteEncontrado+"|"+numIdentificacionClienteEncontrado+"|"+nombresClienteEncontrado+"|"+apellidosClienteEncontrado+"|"+direccionClienteEncontrado+"|"+telefonoClienteEncontrado+"|"+EstadoEnum.ACTIVO.getIndex();
    			salida.println(datos);
    		}else{
    			throw new Exception("No se puedo actualizar la informacion del cliente. Vuelva a interntarlo.");
    		}
    		    		    		
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, e.getMessage());
    	}
    }
    
    
    private void crearCliente(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	final String METHOD_NAME = "[crearCliente]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
    		
    		response.setContentType("text/plain");
    		PrintWriter salida = response.getWriter();

    		int idTipoIdentificacionNuevoCliente          = Integer.parseInt(request.getParameter("idTipoDocumentoNuevoCliente"));
			String numIdentificacionNuevoCliente          = request.getParameter("numIdentificacionNuevoCliente");
			String nombresNuevoCliente                    = request.getParameter("nombresNuevoCliente");
			String apellidosNuevoCliente                  = request.getParameter("apellidosNuevoCliente");
			String direccionNuevoCliente                  = request.getParameter("direccionNuevoCliente");
			String telefonoNuevoCliente                   = request.getParameter("telefonoNuevoCliente");
    		    		
    		Cliente clienteNuevo = new Cliente();
    		
    		clienteNuevo.setIdTipoDocumento(idTipoIdentificacionNuevoCliente);
    		clienteNuevo.setNumeroDocumento(numIdentificacionNuevoCliente);
    		clienteNuevo.setNombre(nombresNuevoCliente);
    		clienteNuevo.setApellido(apellidosNuevoCliente);
    		clienteNuevo.setDireccion(direccionNuevoCliente);
    		clienteNuevo.setTelefono(telefonoNuevoCliente);
    		clienteNuevo.setEstado(EstadoEnum.ACTIVO.getIndex());
    		
    		int idClienteInsertado = clienteEJB.insertarCliente(clienteNuevo);
    		
    		if(idClienteInsertado>0){
    			String datos = idClienteInsertado+"|"+idTipoIdentificacionNuevoCliente+"|"+numIdentificacionNuevoCliente+"|"+nombresNuevoCliente+"|"+apellidosNuevoCliente+"|"+direccionNuevoCliente+"|"+telefonoNuevoCliente+"|"+EstadoEnum.ACTIVO.getIndex();
    			salida.println(datos);
    		}else{
    			throw new Exception("No se puedo registrar la informacion del nuevo cliente. Vuelva a interntarlo.");
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
