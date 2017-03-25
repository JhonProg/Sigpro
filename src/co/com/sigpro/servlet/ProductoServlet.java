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
import javax.servlet.http.HttpSession;

import co.com.sigpro.bean.Producto;
import co.com.sigpro.bean.ProductoCategoria;
import co.com.sigpro.bean.ProductoEstado;
import co.com.sigpro.bean.Usuario;
import co.com.sigpro.constante.SigproConstante;
import co.com.sigpro.ejb.CatalogoEJB;
import co.com.sigpro.ejb.ProductoEJB;
import co.com.sigpro.exception.LogicaException;
import co.com.sigpro.util.Log;

/**
 * Servlet implementation class ProductoServlet
 */
@WebServlet(name="ProductoServlet" , urlPatterns = {"/page/producto"})
public class ProductoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Log logger = new Log();	
	private final String CLASS_NAME = "[ProductoServlet]";
	
	@EJB
	private CatalogoEJB catalogoEJB;
	
	@EJB
	private ProductoEJB productoEJB;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoServlet() {
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
				case "cargarConsultarProducto":
					cargarConsultarProducto(request, response);
					break;
				case "consultarProductos":
					consultarProductos(request, response);
					break;
				case "crearCargarNuevoProducto":
					crearCargarNuevoProducto(request, response);
				case "insertarProducto":
					insertarProducto(request, response);
					break;
				case "cargarEditarProducto":
					cargarEditarProducto(request, response);
					break;
				case "editarProducto":
					editarProducto(request, response);
					break;
				default:
					break;
			}
    		
    	}catch(Exception e){
    		log("Excepcion lanzada. Mensaje:"+e.getMessage());
    	}
    }
    
    private void cargarConsultarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	final String METHOD_NAME = "[cargarConsultarProducto]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
    		HttpSession session = request.getSession();
    		Usuario usuarioLogueado = (Usuario)session.getAttribute(SigproConstante.USUARIO_SESSION);
    		    		
    		List<ProductoCategoria> listaCategoriasProducto = catalogoEJB.consultarProductoCategorias();
    		
    		request.setAttribute("rol", usuarioLogueado.getIdRol());
    		request.setAttribute("listaCategoriasProducto", listaCategoriasProducto);
			
    		request.getRequestDispatcher("../pages/admin/consultaProducto.jsp").forward(request, response);
			
    	}catch(Exception e){
    		response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, e.getMessage());
    	}
    }
    
    
    private void consultarProductos(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	final String METHOD_NAME = "[consultarProductos]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
		
    		List<Producto> productos = new ArrayList<>();
    		HttpSession session = request.getSession();
    		Usuario usuarioLogueado = (Usuario)session.getAttribute(SigproConstante.USUARIO_SESSION);
    		
    		Integer tipoBusquedaProducto = Integer.parseInt(request.getParameter("tipoBusquedaProducto"));
    		
    		switch(tipoBusquedaProducto){
    			case 1:
    				/** Consulta por nombre */
    				final String nombreProducto = request.getParameter("nombreProducto");
    				productos = productoEJB.consultarProductosPorNombre(nombreProducto);
    				break;
    			case 2:
    				/** Consulta por categoria */
    				final Integer idCategoriaProducto = Integer.parseInt(request.getParameter("idCategoriaProducto"));
    				productos = productoEJB.consultarProductosPorCategoria(idCategoriaProducto);
        			break;
    		}
    		
    		logger.info("Se encontraron ["+productos.size()+"] productos...");
    		
    		request.setAttribute("rol", usuarioLogueado.getIdRol());
    		request.setAttribute("productos", productos);
			request.getRequestDispatcher("../pages/admin/listaProductos.jsp").forward(request, response);
			
    	}catch(Exception e){
    		response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, e.getMessage());
    	}
    }
    
    
    private void crearCargarNuevoProducto(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	final String METHOD_NAME = "[crearCargarNuevoProducto]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
		
    		List<ProductoCategoria> productoCategorias = catalogoEJB.consultarProductoCategorias();
    		List<ProductoEstado> productoEstados       = catalogoEJB.consultarProductoEstados();
    		
    		request.setAttribute("listaProductoCategorias", productoCategorias);
    		request.setAttribute("listaProductoEstados", productoEstados);
    		
    		request.getRequestDispatcher("../pages/admin/nuevoProducto.jsp").forward(request, response);
			
    	}catch(Exception e){
    		response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, e.getMessage());
    	}
    }
    
    
    private void insertarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	final String METHOD_NAME = "[insertarProducto]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
    		response.setContentType("text/plain");
    		PrintWriter salida = response.getWriter();
    		    		
    		Integer idProductoCategoria = Integer.parseInt(request.getParameter("idProductoCategoria"));
    		Integer idProductoEstado    = Integer.parseInt(request.getParameter("idProductoEstado"));
    		String sku                  = request.getParameter("sku");
    		String codigoBarras         = request.getParameter("codigoBarras");
    		String nombreProducto       = request.getParameter("nombreProducto");
    		String descripcion          = request.getParameter("descripcion");
    		Integer precioUnitario      = Integer.parseInt(request.getParameter("precioUnitario"));
    		Integer precioDescuento     = Integer.parseInt(request.getParameter("precioDescuento"));
    		Integer cantidadDisponible  = Integer.parseInt(request.getParameter("cantidadDisponible"));
    		    		
    		Producto nuevoProducto = new Producto();
    		
    		nuevoProducto.setIdProductoCategoria(idProductoCategoria);
    		nuevoProducto.setIdProductoEstado(idProductoEstado);
    		nuevoProducto.setSku(sku);
    		nuevoProducto.setCodigoBarras(codigoBarras);
    		nuevoProducto.setNombre(nombreProducto);
    		nuevoProducto.setDescripcion(descripcion);
    		nuevoProducto.setPrecioUnitario(precioUnitario);
    		nuevoProducto.setPrecioDescuento(precioDescuento);
    		nuevoProducto.setCantidad(cantidadDisponible);
    		
    		int resultadoInsercion = productoEJB.insertarProducto(nuevoProducto);
    		
			if (resultadoInsercion>0) {
				
				salida.println(""+resultadoInsercion);
				
			} else {
				throw new LogicaException("Error al actualizar los datos del producto. Intente mas tarde.");
			}
    		
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, e.getMessage());
    	}
    	
    }
    
    private void cargarEditarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	final String METHOD_NAME = "[cargarEditarProducto]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
		
    		Integer idProducto = Integer.parseInt(request.getParameter("idProducto"));
    		
    		logger.info("idProducto : ["+idProducto+"]");
    		
    		Producto productoEditar = productoEJB.consultarProductoPorIdProducto(idProducto);
    		
    		List<ProductoCategoria> productoCategorias = catalogoEJB.consultarProductoCategorias();
    		List<ProductoEstado> productoEstados       = catalogoEJB.consultarProductoEstados();
    		
    		request.setAttribute("listaProductoCategorias", productoCategorias);
    		request.setAttribute("listaProductoEstados", productoEstados);
    		request.setAttribute("producto", productoEditar);
    		    		
    		request.getRequestDispatcher("../pages/admin/editarProducto.jsp").forward(request, response);
			
    	}catch(Exception e){
    		response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, e.getMessage());
    	}
    }
    
    
    private void editarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	final String METHOD_NAME = "[editarProducto]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
    		response.setContentType("text/plain");
    		PrintWriter salida = response.getWriter();
    		
    		Integer idProducto          = Integer.parseInt(request.getParameter("idProducto"));
    		Integer idProductoCategoria = Integer.parseInt(request.getParameter("idProductoCategoria"));
    		Integer idProductoEstado    = Integer.parseInt(request.getParameter("idProductoEstado"));
    		String sku                  = request.getParameter("sku");
    		String codigoBarras         = request.getParameter("codigoBarras");
    		String nombreProducto       = request.getParameter("nombreProducto");
    		String descripcion          = request.getParameter("descripcion");
    		Integer precioUnitario      = Integer.parseInt(request.getParameter("precioUnitario"));
    		Integer precioDescuento     = Integer.parseInt(request.getParameter("precioDescuento"));
    		Integer cantidadDisponible  = Integer.parseInt(request.getParameter("cantidadDisponible"));
    		    		
    		Producto productoEdicion = new Producto();
    		
    		productoEdicion.setIdProducto(idProducto);
    		productoEdicion.setIdProductoCategoria(idProductoCategoria);
    		productoEdicion.setIdProductoEstado(idProductoEstado);
    		productoEdicion.setSku(sku);
    		productoEdicion.setCodigoBarras(codigoBarras);
    		productoEdicion.setNombre(nombreProducto);
    		productoEdicion.setDescripcion(descripcion);
    		productoEdicion.setPrecioUnitario(precioUnitario);
    		productoEdicion.setPrecioDescuento(precioDescuento);
    		productoEdicion.setCantidad(cantidadDisponible);
    		
    		
    		int resultado = productoEJB.editarProducto(productoEdicion);
    		
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
