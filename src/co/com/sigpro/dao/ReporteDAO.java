package co.com.sigpro.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import co.com.sigpro.bean.PedidoPorPromotor;
import co.com.sigpro.bean.VentaPorPromotor;
import co.com.sigpro.exception.DatoException;
import co.com.sigpro.util.Log;

public class ReporteDAO extends DataBaseDAO{

private Log logger = new Log();
	
	private final String CLASS_NAME = "[ReporteDAO]";
	
	public ReporteDAO(){
		
	}
	
	public ReporteDAO(DataSource ds){
		this.dataSource = ds;
	}
	
	public List<PedidoPorPromotor> consultarPedidosPorPromotor() throws DatoException{
		final String METHOD_NAME = "[consultarPedidosPorPromotor]";
		
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		List<PedidoPorPromotor> pedidosPorPromotor = new ArrayList<>();
		PedidoPorPromotor pedidoPorPromotor        = null;
		StringBuffer CONSULTA                      = new StringBuffer();
		
		CONSULTA.append(" SELECT u.nombre AS promotor ,COUNT(p.idpedido) AS cantidad ");
		CONSULTA.append(" FROM pedido p,usuario u ");
		CONSULTA.append(" WHERE p.idusuario=u.idusuario ");
		CONSULTA.append(" GROUP BY u.idusuario ");
		
		logger.info(CONSULTA.toString());
		
		try{
			conexion          = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(CONSULTA.toString());
			resultSet         = preparedStatement.executeQuery();
						
			while(resultSet.next()){
				pedidoPorPromotor = new PedidoPorPromotor();
				
				pedidoPorPromotor.setNombrePromotor(resultSet.getString("promotor"));
				pedidoPorPromotor.setCantidadPedidos(resultSet.getInt("cantidad"));
				
				pedidosPorPromotor.add(pedidoPorPromotor);
			}
			
		}catch(SQLException e){
			logger.error(e.getMessage());
			throw new DatoException(e.getMessage(),e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatoException(e.getMessage(),e);
		} finally {
			closeConnections();
		}
		return pedidosPorPromotor;
	}
	
	
	public List<VentaPorPromotor> consultarVentasPorPromotor() throws DatoException{
		final String METHOD_NAME = "[consultarVentasPorPromotor]";
		
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		List<VentaPorPromotor> ventasPorPromotor = new ArrayList<>();
		VentaPorPromotor ventaPorPromotor        = null;
		StringBuffer CONSULTA                    = new StringBuffer();
		
		CONSULTA.append(" SELECT u.nombre AS promotor,SUM(pd.cantidad*(pr.preciounitario-pr.preciodescuento)) AS ventas ");
		CONSULTA.append(" FROM pedido_detalle pd,pedido pe, usuario u,producto pr ");
		CONSULTA.append(" WHERE pd.idpedido = pe.idpedido ");
		CONSULTA.append(" AND pe.idusuario=u.idusuario ");
		CONSULTA.append(" AND pd.idproducto=pr.idproducto ");
		CONSULTA.append(" GROUP BY (u.idusuario) ");
		
		logger.info(CONSULTA.toString());
		
		try{
			conexion          = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(CONSULTA.toString());
			resultSet         = preparedStatement.executeQuery();
						
			while(resultSet.next()){
				ventaPorPromotor = new VentaPorPromotor();
				
				ventaPorPromotor.setNombrePromotor(resultSet.getString("promotor"));
				ventaPorPromotor.setTotalVentas(resultSet.getInt("ventas"));
				
				ventasPorPromotor.add(ventaPorPromotor);
			}
			
		}catch(SQLException e){
			logger.error(e.getMessage());
			throw new DatoException(e.getMessage(),e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatoException(e.getMessage(),e);
		} finally {
			closeConnections();
		}
		return ventasPorPromotor;
	}
	
	
	public List<VentaPorPromotor> consultarVentasPorProducto() throws DatoException{
		final String METHOD_NAME = "[consultarVentasPorProducto]";
		
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		List<VentaPorPromotor> ventasPorPromotor = new ArrayList<>();
		VentaPorPromotor ventaPorPromotor        = null;
		StringBuffer CONSULTA                    = new StringBuffer();
		
		CONSULTA.append(" SELECT pr.nombre as producto,SUM(pd.cantidad*(pr.preciounitario-pr.preciodescuento)) AS ventas ");
		CONSULTA.append(" FROM pedido_detalle pd,pedido pe, usuario u,producto pr ");
		CONSULTA.append(" WHERE pd.idpedido = pe.idpedido ");
		CONSULTA.append(" AND pe.idusuario=u.idusuario ");
		CONSULTA.append(" AND pd.idproducto=pr.idproducto ");
		CONSULTA.append(" group by(pr.idproducto); ");
		
		logger.info(CONSULTA.toString());
		
		try{
			conexion          = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(CONSULTA.toString());
			resultSet         = preparedStatement.executeQuery();
						
			while(resultSet.next()){
				ventaPorPromotor = new VentaPorPromotor();
				
				ventaPorPromotor.setNombrePromotor(resultSet.getString("producto"));
				ventaPorPromotor.setTotalVentas(resultSet.getInt("ventas"));
				
				ventasPorPromotor.add(ventaPorPromotor);
			}
			
		}catch(SQLException e){
			logger.error(e.getMessage());
			throw new DatoException(e.getMessage(),e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatoException(e.getMessage(),e);
		} finally {
			closeConnections();
		}
		return ventasPorPromotor;
	}
	
	public int consultarTotalVentasPorMes(int mes) throws DatoException{
		final String METHOD_NAME = "[consultarTotalVentasPorMes]";
		
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		int ventas = 0;
		StringBuffer CONSULTA = new StringBuffer();
		
		CONSULTA.append(" SELECT SUM(pd.cantidad*(pr.preciounitario-pr.preciodescuento)) AS ventas ");
		CONSULTA.append(" FROM pedido_detalle pd,pedido pe, usuario u,producto pr ");
		CONSULTA.append(" WHERE pd.idpedido = pe.idpedido ");
		CONSULTA.append(" AND pe.idusuario=u.idusuario ");
		CONSULTA.append(" AND pd.idproducto=pr.idproducto ");
		CONSULTA.append(" AND pe.mes= "+mes);
		
		logger.info(CONSULTA.toString());
		
		try{
			conexion          = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(CONSULTA.toString());
			resultSet         = preparedStatement.executeQuery();
						
			if(resultSet.next()){
				ventas = resultSet.getInt("ventas");
			}
			
		}catch(SQLException e){
			logger.error(e.getMessage());
			throw new DatoException(e.getMessage(),e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatoException(e.getMessage(),e);
		} finally {
			closeConnections();
		}
		return ventas;
	}
	
}
