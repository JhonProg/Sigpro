package co.com.sigpro.ejb;

import java.util.List;

import javax.ejb.Stateless;

import co.com.sigpro.bean.Carrito;
import co.com.sigpro.bean.MiPedido;
import co.com.sigpro.bean.Pedido;
import co.com.sigpro.dao.PedidoDAO;
import co.com.sigpro.exception.DatoException;
import co.com.sigpro.exception.LogicaException;
import co.com.sigpro.util.Log;

@Stateless
public class PedidoEJB extends DataBaseEJB{
	private Log logger = new Log();
	
	public int consultarPedidoVigentePorIdCliente(final int idCliente,final int idUsuarioCrea) throws LogicaException{
		try{
			PedidoDAO pedidoDAO = new PedidoDAO(ds_consulta);
			return pedidoDAO.consultarPedidoVigentePorIdCliente(idCliente,idUsuarioCrea);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	}
		
	public int crearPedido(final int estado,final int idCliente,final int idUsuarioCrea) throws LogicaException{
		try{
			PedidoDAO pedidoDAO = new PedidoDAO(ds_consulta);
			return pedidoDAO.crearPedido(estado,idCliente,idUsuarioCrea);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	
	}
		
	public int insertarPedidoDetalle(final int idPedido,final int idProducto,final int cantidad) throws LogicaException{
		try{
			PedidoDAO pedidoDAO = new PedidoDAO(ds_consulta);
			return pedidoDAO.insertarPedidoDetalle(idPedido,idProducto,cantidad);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	
	}
	
	public boolean productoYaEstaEnPedido(final int idPedido,final int idProducto) throws LogicaException{
		try{
			PedidoDAO pedidoDAO = new PedidoDAO(ds_consulta);
			return pedidoDAO.productoYaEstaEnPedido(idPedido,idProducto);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	}
	
	
	
	public int actualizarCantidadProducto(final int idPedido,final int idProducto,final int cantidad) throws LogicaException{
		try{
			PedidoDAO pedidoDAO = new PedidoDAO(ds_consulta);
			return pedidoDAO.actualizarCantidadProducto(idPedido,idProducto,cantidad);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	
	}
	
	

	public int actualizarCantidadProductoEnInventario(final int idProducto,final int cantidad) throws LogicaException{
		try{
			PedidoDAO pedidoDAO = new PedidoDAO(ds_consulta);
			return pedidoDAO.actualizarCantidadProductoEnInventario(idProducto,cantidad);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	
	}
	
	
	
	public int actualizarEstadoPedido(final int idPedido,final int estadoNuevo,final int mes) throws LogicaException{
		try{
			PedidoDAO pedidoDAO = new PedidoDAO(ds_consulta);
			return pedidoDAO.actualizarEstadoPedido(idPedido,estadoNuevo,mes);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	
	}
	
	public int actualizarEstadoPedido(final int idPedido,final int estadoNuevo) throws LogicaException{
		try{
			PedidoDAO pedidoDAO = new PedidoDAO(ds_consulta);
			return pedidoDAO.actualizarEstadoPedido(idPedido,estadoNuevo);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	
	}
	
	public Pedido consultarUltimoPedidoCreadoPorIdUsuarioEstadoPedido(final int idPedido,final int estado,final int idCliente) throws LogicaException{
		try{
			PedidoDAO pedidoDAO = new PedidoDAO(ds_consulta);
			return pedidoDAO.consultarUltimoPedidoCreadoPorIdUsuarioEstadoPedido(idPedido,estado,idCliente);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	
	}
	
	
	
	
	public List<Carrito> consultarCarritoDetalle(final int idPedido) throws LogicaException{
		try{
			PedidoDAO pedidoDAO = new PedidoDAO(ds_consulta);
			return pedidoDAO.consultarCarritoDetalle(idPedido);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	
	
	}
	
	
	public List<MiPedido> consultarPedidosPorUsuario(final int idUsuario) throws LogicaException{
		try{
			PedidoDAO pedidoDAO = new PedidoDAO(ds_consulta);
			return pedidoDAO.consultarPedidosPorUsuario(idUsuario);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	
	
	}
	
}