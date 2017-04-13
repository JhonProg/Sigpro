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

import co.com.sigpro.bean.Carrito;
import co.com.sigpro.bean.MiPedido;
import co.com.sigpro.bean.Pedido;
import co.com.sigpro.bean.Producto;
import co.com.sigpro.bean.ProductoCategoria;
import co.com.sigpro.bean.TipoDocumento;
import co.com.sigpro.bean.Usuario;
import co.com.sigpro.constante.EstadoPedidoEnum;
import co.com.sigpro.constante.SigproConstante;
import co.com.sigpro.ejb.CatalogoEJB;
import co.com.sigpro.ejb.PedidoEJB;
import co.com.sigpro.ejb.ProductoEJB;
import co.com.sigpro.util.Log;

/**
 * Servlet implementation class ProductoServlet
 */
@WebServlet(name="PedidoServlet" , urlPatterns = {"/page/pedido"})
public class PedidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Log logger = new Log();	
	private final String CLASS_NAME = "[ProductoServlet]";	
	
	@EJB
	private CatalogoEJB catalogoEJB;
	
	@EJB
	private ProductoEJB productoEJB;
	
	@EJB
	private PedidoEJB pedidoEJB;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PedidoServlet() {
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
				case "cargarCrearPedido":
					cargarCrearPedido(request, response);
					break;
				case "consultarProductos":
					consultarProductos(request, response);
					break;
				case "confirmarCantidad":
					confirmarCantidad(request, response);
					break;
				case "agregarProductoAlCarrito":
					agregarProductoAlCarrito(request, response);
					break;
				case "consultarCarritoCompras":
					consultarCarritoCompras(request, response);
					break;
				case "pasarPedidoAVenta":
					pasarPedidoAVenta(request, response);
					break;
				case "misPedidos":
					consultarMisPedidos(request, response);
					break;
				case "consultarDetallePedido":
					consultarDetallePedido(request, response);
					break;
				case "cambiarEstadoPedido":
					cambiarEstadoPedido(request, response);
					break;
				case "eliminarProductoDelCarrito":
					eliminarProductoDelCarrito(request, response);
					break;
				default:
					break;
			}
    		
    	}catch(Exception e){
    		log("Excepcion lanzada. Mensaje:"+e.getMessage());
    	}
    }
    
    private void cargarCrearPedido(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	final String METHOD_NAME = "[cargarCrearPedido]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
    		
    		List<ProductoCategoria> listaCategoriasProducto = catalogoEJB.consultarProductoCategorias();
    		List<TipoDocumento> listaTiposDocumento = catalogoEJB.consultarTiposDocumento();
    		
    		request.setAttribute("listaCategoriasProducto", listaCategoriasProducto);
    		request.setAttribute("listaTiposDocumento", listaTiposDocumento);
    		request.getRequestDispatcher("../pages/promotor/nuevoPedido.jsp").forward(request, response);
    		
    	}catch(Exception e){
    		response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, e.getMessage());
    	}
    }
    
    
    private void consultarProductos(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	final String METHOD_NAME = "[consultarProductos]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
		
    		List<Producto> productos     = new ArrayList<>();
    		Integer tipoBusquedaProducto = Integer.parseInt(request.getParameter("tipoBusquedaProductoPedido"));
    		String idCliente             = request.getParameter("idCliente");
    		
    		switch(tipoBusquedaProducto){
    			case 1:
    				/** Consulta por nombre */
    				final String nombreProducto = request.getParameter("nombreProductoPedido");
    				productos = productoEJB.consultarProductosPorNombre(nombreProducto);
    				break;
    			case 2:
    				/** Consulta por categoria */
    				final Integer idCategoriaProducto = Integer.parseInt(request.getParameter("idCategoriaProductoPedido"));
    				productos = productoEJB.consultarProductosPorCategoria(idCategoriaProducto);
        			break;
    		}
    		
    		request.setAttribute("idCliente", idCliente);
    		request.setAttribute("productos", productos);
			request.getRequestDispatcher("../pages/promotor/listaProductos.jsp").forward(request, response);
			
    	}catch(Exception e){
    		response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, e.getMessage());
    	}
    }
    
    
    
    private void confirmarCantidad(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	final String METHOD_NAME = "[confirmarCantidad]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
    		
    		String idCliente  = request.getParameter("idCliente");
    		String idProducto = request.getParameter("idProducto");
    		String cantidad   = request.getParameter("cantidad");
    		
    		logger.info(idCliente+","+idProducto+","+cantidad);
    		
    		request.setAttribute("idCliente", idCliente);
    		request.setAttribute("idProducto", idProducto);
    		request.setAttribute("cantidad", cantidad);
    		
    		request.getRequestDispatcher("../pages/promotor/confirmarCantidad.jsp").forward(request, response);
    		
    	}catch(Exception e){
    		response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, e.getMessage());
    	}
    }
    
    
    private void agregarProductoAlCarrito(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	final String METHOD_NAME = "[agregarProductoAlCarrito]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
    		HttpSession session = request.getSession();
    		Usuario usuarioLogueado = (Usuario)session.getAttribute(SigproConstante.USUARIO_SESSION);
    		
    		response.setContentType("text/plain");
    		PrintWriter salida = response.getWriter();
    		
    		Integer idCliente  = Integer.parseInt(request.getParameter("idCliente"));
    		Integer idProducto = Integer.parseInt(request.getParameter("idProducto"));
    		Integer cantidad   = Integer.parseInt(request.getParameter("cantidad"));
    		
    		/** validar si el cliente tiene un pedido CREADO O PREPARADO */
    		int idPedido = pedidoEJB.consultarPedidoVigentePorIdCliente(idCliente,usuarioLogueado.getIdUsuario());
    		
    		logger.info("idPedidoVigente : ["+idPedido+"]");
    		
    		if(idPedido==0){ /** No tiene carrito */
    			idPedido = pedidoEJB.crearPedido(EstadoPedidoEnum.CREADO.getIndex(),idCliente,usuarioLogueado.getIdUsuario());
    			logger.info("idPedidoCreado : ["+idPedido+"]");
    		}
    		    		
    		// si el producto existe aumentar la cantidad
    		boolean productoRegistradoEnCarrito = pedidoEJB.productoYaEstaEnPedido(idPedido,idProducto);
    		
    		int pedidoDetalleRegistradoActualizado = 0;
    		
    		if(productoRegistradoEnCarrito){
    			pedidoDetalleRegistradoActualizado = pedidoEJB.actualizarCantidadProducto(idPedido,idProducto,cantidad);
    		}else{
    			pedidoDetalleRegistradoActualizado = pedidoEJB.insertarPedidoDetalle(idPedido,idProducto,cantidad);
    		}
    		
    		if(pedidoDetalleRegistradoActualizado>0){
    			
    			int actualizoInventario = pedidoEJB.actualizarCantidadProductoEnInventario(idProducto,cantidad);
    			
    			if(actualizoInventario>0){
    				logger.info("inventario actualizado exitosamente...");
    			}else{
    				logger.error("no se pudo actualizar el inventario...");
    			}
    			
    			salida.println(pedidoDetalleRegistradoActualizado);
    		}else{
    			throw new Exception("No fue posible agregar el producto al carrito de compras.Vuelva a intentarlo.");
    		}
    		    		
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, e.getMessage());
    	}
    }
    

	private void consultarCarritoCompras(HttpServletRequest request, HttpServletResponse response) throws IOException{
		final String METHOD_NAME = "[consultarCarritoCompras]";
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		try{
			HttpSession session = request.getSession();
			Usuario usuarioLogueado = (Usuario)session.getAttribute(SigproConstante.USUARIO_SESSION);
			List<Carrito> listaCarritoDetalle = new ArrayList<>();
			
			int idUsuario = usuarioLogueado.getIdUsuario();
			int idCliente = Integer.parseInt(request.getParameter("idCliente"));
			
			logger.info("idUsuario = ["+idUsuario+"]");
			
			Pedido pedido = pedidoEJB.consultarUltimoPedidoCreadoPorIdUsuarioEstadoPedido(idUsuario,EstadoPedidoEnum.CREADO.getIndex(),idCliente);
			
			if(pedido==null){
				throw new Exception("No se ha encontrado un pedido creado para el usuario...");
			}else{
				int idPedido = pedido.getIdpedido();
				listaCarritoDetalle = pedidoEJB.consultarCarritoDetalle(idPedido);
			}
			
			int elementosCarrito = listaCarritoDetalle.size();
			
			if(elementosCarrito>0){
				double total = 0;
				
				for(Carrito c:listaCarritoDetalle){
					Producto p = c.getProducto();
					double subTotal = 0;
					if(p.getPrecioDescuento()>0){
						subTotal = p.getCantidad()*p.getPrecioDescuento();
						p.setSubTotal(subTotal);
						total=total+subTotal;
					}else{
						subTotal = p.getCantidad()*p.getPrecioUnitario();
						p.setSubTotal(subTotal);
						total=total+subTotal;
					}
					
				}
				
				request.setAttribute("idPedido", pedido.getIdpedido());
				request.setAttribute("total", total);
				request.setAttribute("listaCarritoDetalle", listaCarritoDetalle);
	    		request.getRequestDispatcher("../pages/promotor/carritoDetalle.jsp").forward(request, response);
			}else{
				logger.info("NO Hay elementos en el carro");
				throw new Exception("No se ha encontraron productos para mostrar...");
			}
						
		}catch(Exception e){
			logger.error(e.getMessage());
			response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, e.getMessage());
		}
	}

	
	private void pasarPedidoAVenta(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	final String METHOD_NAME = "[pasarPedidoAVenta]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
    		
    		response.setContentType("text/plain");
    		PrintWriter salida = response.getWriter();
    		
    		Integer idPedido  = Integer.parseInt(request.getParameter("idPedido"));
    		Integer mesPedido = Integer.parseInt(request.getParameter("mesPedido"));
    		
    		int actualizoPedido = pedidoEJB.actualizarEstadoPedido(idPedido,EstadoPedidoEnum.EN_VENTA.getIndex(),mesPedido);
    		
    		if(actualizoPedido>0){
    			salida.println(actualizoPedido);
    		}else{
    			throw new Exception("No fue posible finalizar el pedido..Vuelva a intentarlo.");
    		}
    		    		
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, e.getMessage());
    	}
    }
	 
	 
	private void consultarMisPedidos(HttpServletRequest request, HttpServletResponse response) throws IOException{
	    	final String METHOD_NAME = "[consultarMisPedidos]";
	    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
	    	
	    	try{
			
	    		HttpSession session = request.getSession();
				Usuario usuarioLogueado = (Usuario)session.getAttribute(SigproConstante.USUARIO_SESSION);
				
				int idUsuario = usuarioLogueado.getIdUsuario();
	    		
	    		List<MiPedido> listaMisPedidos = pedidoEJB.consultarPedidosPorUsuario(idUsuario);
	    		request.setAttribute("listaMisPedidos", listaMisPedidos);
				request.getRequestDispatcher("../pages/promotor/listaPedidos.jsp").forward(request, response);
				
	    	}catch(Exception e){
	    		response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, e.getMessage());
	    	}
	    }
	 
	
	private void consultarDetallePedido(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	final String METHOD_NAME = "[consultarDetallePedido]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
    		List<Carrito> listaPedidoDetalle = new ArrayList<>();
    		
    		int idPedido = Integer.parseInt(request.getParameter("idPedido"));
    		listaPedidoDetalle = pedidoEJB.consultarCarritoDetalle(idPedido);
			
			final int elementosCarrito = listaPedidoDetalle.size();
			
			if(elementosCarrito>0){
				double total = 0;
				
				for(Carrito c:listaPedidoDetalle){
					Producto p = c.getProducto();
					double subTotal = 0;
					if(p.getPrecioDescuento()>0){
						subTotal = p.getCantidad()*p.getPrecioDescuento();
						p.setSubTotal(subTotal);
						total=total+subTotal;
					}else{
						subTotal = p.getCantidad()*p.getPrecioUnitario();
						p.setSubTotal(subTotal);
						total=total+subTotal;
					}
					
				}
				
				request.setAttribute("idPedido", idPedido);
				request.setAttribute("total", total);
				request.setAttribute("listaPedidoDetalle", listaPedidoDetalle);
	    		request.getRequestDispatcher("../pages/promotor/pedidoDetalle.jsp").forward(request, response);
			}else{
				logger.info("NO se encontro el detalle del pedido ["+idPedido+"]");
				throw new Exception("NO se encontro el detalle del pedido ["+idPedido+"]. Intente mas tarde.");
			}
    	}catch(Exception e){
    		response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, e.getMessage());
    	}
    }
	
	
	private void cambiarEstadoPedido(HttpServletRequest request, HttpServletResponse response) throws IOException{
		try{
			response.setContentType("text/plain");
			PrintWriter salida = response.getWriter();
			
			Integer idPedido            = Integer.parseInt(request.getParameter("idPedido"));
    		Integer idNuevoEstadoPedido = Integer.parseInt(request.getParameter("idNuevoEstadoPedido"));
			
    		int actualizados = pedidoEJB.actualizarEstadoPedido(idPedido, idNuevoEstadoPedido);
    		
    		if(actualizados>0){
    			salida.println(actualizados);
    		}else{
    			throw new Exception("No fue posible actualizar el estado del pedido. Intentelo más tarde.");
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
