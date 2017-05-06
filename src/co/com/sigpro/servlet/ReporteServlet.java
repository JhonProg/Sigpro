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

import co.com.sigpro.bean.PedidoPorPromotor;
import co.com.sigpro.bean.VentaPorPromotor;
import co.com.sigpro.ejb.ReporteEJB;
import co.com.sigpro.util.Calendar;
import co.com.sigpro.util.Log;

/**
 * Servlet implementation class ClienteServlet
 */
@WebServlet(name="ReporteServlet" , urlPatterns = {"/page/reporte"})
public class ReporteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;    
	private Log logger = new Log();	
	private final String CLASS_NAME = "[ReporteServlet]";
	
	@EJB
	private ReporteEJB reporteEJB;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReporteServlet() {
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
				case "cargarVerReportesGraficos":
					cargarVerReportesGraficos(request, response);
					break;
				case "cargarVerReportesGraficosGerente":
					cargarVerReportesGraficosGerente(request, response);
					break;
				case "compararVentasEntreDosMeses":
					compararVentasEntreDosMeses(request, response);
					break;
				default:
					break;
			}
    		
    	}catch(Exception e){
    		log("Excepcion lanzada. Mensaje:"+e.getMessage());
    	}
    }
    
    private void cargarVerReportesGraficos(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	final String METHOD_NAME = "[validarSiExisteCliente]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
    		
    		/** Consultar datos reporte 1: Cantidad de pedidos por promotor. */
    		List<PedidoPorPromotor> pedidosPorPromotor = reporteEJB.consultarPedidosPorPromotor();
    		
    		/** Consultar datos reporte 2: Cantidad de ventas ($) por promotor. */
    		List<VentaPorPromotor> ventasPorPromotor = reporteEJB.consultarVentasPorPromotor();
    		
    		/** ------ Alistamiento datos [1] ---------- */
    		String _pedidosPorPromotor = "";
    		int contadorPedidos        = 0;
    		
    		for(PedidoPorPromotor pedidoPorPromotor : pedidosPorPromotor){
    			if(contadorPedidos==0){
    				/** Es el primero */
    				_pedidosPorPromotor = pedidoPorPromotor.getNombrePromotor()+","+pedidoPorPromotor.getCantidadPedidos();
    			}else{
    				_pedidosPorPromotor = _pedidosPorPromotor+"|"+pedidoPorPromotor.getNombrePromotor()+","+pedidoPorPromotor.getCantidadPedidos();
    			}
    			contadorPedidos++;
    		}
    		
    		/** ------ Alistamiento datos [2] ---------- */
    		String _ventasPorPromotor = "";
    		int contadorVentas        = 0;
    		
    		for(VentaPorPromotor ventaPorPromotor : ventasPorPromotor){
    			if(contadorVentas==0){
    				/** Es el primero */
    				_ventasPorPromotor = ventaPorPromotor.getNombrePromotor()+","+ventaPorPromotor.getTotalVentas();
    			}else{
    				_ventasPorPromotor = _ventasPorPromotor+"|"+ventaPorPromotor.getNombrePromotor()+","+ventaPorPromotor.getTotalVentas();
    			}
    			contadorVentas++;
    		}
    		    		
    		logger.info("_ventasPorPromotor : "+_ventasPorPromotor);
    		
    		request.setAttribute("datosReportePedidosPorPromotor",_pedidosPorPromotor);
    		request.setAttribute("datosReporteVentasPorPromotor",_ventasPorPromotor);
    		
    		request.getRequestDispatcher("../pages/director/reportesPrincipal.jsp").forward(request, response);
    		
    	}catch(Exception e){
    		response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, e.getMessage());
    	}
    }
    
    private void cargarVerReportesGraficosGerente(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	final String METHOD_NAME = "[cargarVerReportesGraficosGerente]";
    	logger.info(CLASS_NAME+"-"+METHOD_NAME);
    	
    	try{
    		
    		/** Consultar datos reporte 1: Cantidad de pedidos por promotor. */
    		List<PedidoPorPromotor> pedidosPorPromotor = reporteEJB.consultarPedidosPorPromotor();
    		
    		/** Consultar datos reporte 2: Cantidad de ventas ($) por promotor. */
    		List<VentaPorPromotor> ventasPorPromotor = reporteEJB.consultarVentasPorPromotor();
    		
    		/** ------ Alistamiento datos [1] ---------- */
    		String _pedidosPorPromotor = "";
    		int contadorPedidos        = 0;
    		
    		for(PedidoPorPromotor pedidoPorPromotor : pedidosPorPromotor){
    			if(contadorPedidos==0){
    				/** Es el primero */
    				_pedidosPorPromotor = pedidoPorPromotor.getNombrePromotor()+","+pedidoPorPromotor.getCantidadPedidos();
    			}else{
    				_pedidosPorPromotor = _pedidosPorPromotor+"|"+pedidoPorPromotor.getNombrePromotor()+","+pedidoPorPromotor.getCantidadPedidos();
    			}
    			contadorPedidos++;
    		}
    		
    		/** ------ Alistamiento datos [2] ---------- */
    		String _ventasPorPromotor = "";
    		int contadorVentas        = 0;
    		
    		for(VentaPorPromotor ventaPorPromotor : ventasPorPromotor){
    			if(contadorVentas==0){
    				/** Es el primero */
    				_ventasPorPromotor = ventaPorPromotor.getNombrePromotor()+","+ventaPorPromotor.getTotalVentas();
    			}else{
    				_ventasPorPromotor = _ventasPorPromotor+"|"+ventaPorPromotor.getNombrePromotor()+","+ventaPorPromotor.getTotalVentas();
    			}
    			contadorVentas++;
    		}
    		    		
    		logger.info("_ventasPorPromotor : "+_ventasPorPromotor);
    		
    		request.setAttribute("datosReportePedidosPorPromotor",_pedidosPorPromotor);
    		request.setAttribute("datosReporteVentasPorPromotor",_ventasPorPromotor);
    		
    		request.getRequestDispatcher("../pages/gerente/reportesPrincipal.jsp").forward(request, response);
    		
    	}catch(Exception e){
    		response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, e.getMessage());
    	}
    }
    
    private void compararVentasEntreDosMeses(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	logger.info("compararVentasEntreDosMeses");
    	
    	try{
    		
    		response.setContentType("text/plain");
    		PrintWriter salida = response.getWriter();
    		
    		int idMesUno = Integer.parseInt(request.getParameter("idMesUno"));
    		int idMesDos = Integer.parseInt(request.getParameter("idMesDos"));
    		
    		int ventaMesUno = reporteEJB.consultarTotalVentasPorMes(idMesUno);
    		int ventaMesDos = reporteEJB.consultarTotalVentasPorMes(idMesDos);
    		
    		String valoresReporte = Calendar.getNombreMesPorNumero(idMesUno)+","+ventaMesUno+"|"+Calendar.getNombreMesPorNumero(idMesDos)+","+ventaMesDos;
    		
    		salida.println(valoresReporte);
    		
    	}catch(Exception e){
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
