package co.com.sigpro.ejb;

import java.util.List;

import javax.ejb.Stateless;

import co.com.sigpro.bean.PedidoPorPromotor;
import co.com.sigpro.bean.VentaPorPromotor;
import co.com.sigpro.dao.ReporteDAO;
import co.com.sigpro.exception.DatoException;
import co.com.sigpro.exception.LogicaException;
import co.com.sigpro.util.Log;

@Stateless
public class ReporteEJB extends DataBaseEJB {

	private Log logger = new Log();
	
	public List<PedidoPorPromotor> consultarPedidosPorPromotor() throws LogicaException{
		try{
			ReporteDAO reporteDAO = new ReporteDAO(ds_consulta);
			return reporteDAO.consultarPedidosPorPromotor();
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	}
	
	
	public List<VentaPorPromotor> consultarVentasPorPromotor() throws LogicaException{
		try{
			ReporteDAO reporteDAO = new ReporteDAO(ds_consulta);
			return reporteDAO.consultarVentasPorPromotor();
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	}
	
	
	public List<VentaPorPromotor> consultarVentasPorProducto() throws LogicaException{
		try{
			ReporteDAO reporteDAO = new ReporteDAO(ds_consulta);
			return reporteDAO.consultarVentasPorProducto();
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	}
	

	public int consultarTotalVentasPorMes(int mes) throws LogicaException{
		try{
			ReporteDAO reporteDAO = new ReporteDAO(ds_consulta);
			return reporteDAO.consultarTotalVentasPorMes(mes);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	}
}
