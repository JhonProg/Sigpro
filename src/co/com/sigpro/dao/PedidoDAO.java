package co.com.sigpro.dao;


import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import co.com.sigpro.bean.Carrito;
import co.com.sigpro.bean.Cliente;
import co.com.sigpro.bean.MiPedido;
import co.com.sigpro.bean.Pedido;
import co.com.sigpro.bean.PedidoDetalle;
import co.com.sigpro.bean.Producto;
import co.com.sigpro.bean.ProductoCategoria;
import co.com.sigpro.bean.Rol;
import co.com.sigpro.bean.TipoDocumento;
import co.com.sigpro.bean.Usuario;
import co.com.sigpro.constante.EstadoEnum;
import co.com.sigpro.constante.EstadoPedidoEnum;
import co.com.sigpro.exception.DatoException;
import co.com.sigpro.exception.LogicaException;
import co.com.sigpro.util.Log;


/**
 * Clase para manejo de operaciones con los catalogos
 * */
public class PedidoDAO extends DataBaseDAO{

	private Log logger = new Log();
	
	private final String CLASS_NAME = "[PedidoDAO]";
	
	/**
	 * Constructor.
	 * */
	public PedidoDAO(){
	}
	
	/**
	 * Constructor. Recibe la conexion a la fuente de datos.
	 * @param DataSource dataSource
	 * */
	public PedidoDAO(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	public int consultarPedidoVigentePorIdCliente(final int idCliente,final int idUsuarioCrea) throws DatoException{
		final String METHOD_NAME = "[consultarPedidoVigentePorIdCliente]";
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		int idPedido          = 0;
		StringBuffer CONSULTA = new StringBuffer();
		
		CONSULTA.append(" SELECT idpedido FROM pedido WHERE idpedidoestado in(?,?) AND idcliente=? AND idusuario=?");
		
		logger.info(CONSULTA.toString());
		
		try{
			conexion          = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(CONSULTA.toString());
			
			preparedStatement.setInt(1, EstadoPedidoEnum.CREADO.getIndex());
			preparedStatement.setInt(2, EstadoPedidoEnum.PREPARADO.getIndex());
			preparedStatement.setInt(3, idCliente);
			preparedStatement.setInt(4, idUsuarioCrea);
			
			resultSet         = preparedStatement.executeQuery();
						
			if(resultSet.next()){
				idPedido = resultSet.getInt("idpedido");
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
		
		return idPedido;
	}
	

	public boolean productoYaEstaEnPedido(final int idPedido,final int idProducto) throws DatoException{
		final String METHOD_NAME = "[productoYaEstaEnPedido]";
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		boolean productoEsta  = false;
		StringBuffer CONSULTA = new StringBuffer();
		
		CONSULTA.append(" SELECT idpedidodetalle FROM pedido_detalle WHERE idpedido=? AND idproducto=? LIMIT 1 ");
		
		logger.info(CONSULTA.toString());
		
		try{
			conexion          = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(CONSULTA.toString());
			
			preparedStatement.setInt(1, idPedido);
			preparedStatement.setInt(2, idProducto);
						
			resultSet = preparedStatement.executeQuery();
						
			if(resultSet.next()){
				productoEsta = true;
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
		
		return productoEsta;
	}
	
	public int crearPedido(final int estado,final int idCliente,final int idUsuarioCrea) throws DatoException {
		int idPedidoInsertado = 0;
		final String METHOD_NAME = "[crearPedido]";
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		try {

			StringBuffer INSERCION = new StringBuffer();
			
			INSERCION.append(" INSERT INTO pedido(idpedidoestado,idcliente,idusuario) VALUES(?,?,?); ");
			
			logger.info(INSERCION.toString());
			
			conexion = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(INSERCION.toString(),Statement.RETURN_GENERATED_KEYS);
		
			preparedStatement.setInt(1, estado);
			preparedStatement.setInt(2, idCliente);
			preparedStatement.setInt(3, idUsuarioCrea);
			
			idPedidoInsertado = idPedidoInsertado + preparedStatement.executeUpdate();
			
			if(idPedidoInsertado==0){
				throw new DatoException("No fue posible registrar al nuevo pedido.");
			}else{
				resultSet = preparedStatement.getGeneratedKeys();
				resultSet.next();
				idPedidoInsertado = resultSet.getInt(1);
			}
			
			logger.info("estatus:["+idPedidoInsertado+"]");
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DatoException(e.getMessage(),e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatoException(e.getMessage(),e);
		} finally {
			closeConnections();
		}
		return idPedidoInsertado;
	}
	
	
	public int actualizarCantidadProducto(final int idPedido,final int idProducto,final int cantidad) throws DatoException {
		int actualizado = 0;
		final String METHOD_NAME = "[actualizarCantidadProducto]";
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		try {

			StringBuffer ACTUALIZACION = new StringBuffer();
			
			ACTUALIZACION.append(" UPDATE pedido_detalle SET cantidad=cantidad+? WHERE idpedido=? AND idproducto=? ");
			
			logger.info(ACTUALIZACION.toString());
			
			conexion = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(ACTUALIZACION.toString(),Statement.RETURN_GENERATED_KEYS);
		
			preparedStatement.setInt(1, cantidad);
			preparedStatement.setInt(2, idPedido);
			preparedStatement.setInt(3, idProducto);
			
			actualizado = actualizado + preparedStatement.executeUpdate();
			
			if(actualizado==0){
				throw new DatoException("No fue posible registrar al nuevo pedido.");
			}else{
				resultSet = preparedStatement.getGeneratedKeys();
				resultSet.next();
				actualizado = resultSet.getInt(1);
			}
			
			logger.info("estatus:["+actualizado+"]");
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DatoException(e.getMessage(),e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatoException(e.getMessage(),e);
		} finally {
			closeConnections();
		}
		return actualizado;
	}
	
	
	public int actualizarEstadoPedido(final int idPedido,final int estadoNuevo) throws DatoException {
		int actualizado = 0;
		final String METHOD_NAME = "[actualizarEstadoPedido]";
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		try {

			StringBuffer ACTUALIZACION = new StringBuffer();
			
			ACTUALIZACION.append(" UPDATE pedido SET idpedidoestado=? WHERE idpedido=? ");
			
			logger.info(ACTUALIZACION.toString());
			
			conexion = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(ACTUALIZACION.toString(),Statement.RETURN_GENERATED_KEYS);
		
			preparedStatement.setInt(1, estadoNuevo);
			preparedStatement.setInt(2, idPedido);
			
			actualizado = actualizado + preparedStatement.executeUpdate();
			
			if(actualizado==0){
				throw new DatoException("No fue posible actualizar el pedido.");
			}else{
				resultSet = preparedStatement.getGeneratedKeys();
				resultSet.next();
				actualizado = resultSet.getInt(1);
			}
			
			logger.info("estatus:["+actualizado+"]");
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DatoException(e.getMessage(),e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatoException(e.getMessage(),e);
		} finally {
			closeConnections();
		}
		return actualizado;
	}	
	
	public int actualizarCantidadProductoEnInventario(final int idProducto,final int cantidad) throws DatoException {
		int actualizado = 0;
		final String METHOD_NAME = "[actualizarCantidadProductoEnInventario]";
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		try {

			StringBuffer ACTUALIZACION = new StringBuffer();
			
			ACTUALIZACION.append(" UPDATE producto SET cantidad=cantidad-? WHERE idproducto=? ");
			
			logger.info(ACTUALIZACION.toString());
			
			conexion = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(ACTUALIZACION.toString(),Statement.RETURN_GENERATED_KEYS);
		
			preparedStatement.setInt(1, cantidad);
			preparedStatement.setInt(2, idProducto);
			
			actualizado = actualizado + preparedStatement.executeUpdate();
			
			if(actualizado==0){
				throw new DatoException("No fue posible actualizar el inventario.");
			}else{
				resultSet = preparedStatement.getGeneratedKeys();
				resultSet.next();
				actualizado = resultSet.getInt(1);
			}
			
			logger.info("estatus:["+actualizado+"]");
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DatoException(e.getMessage(),e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatoException(e.getMessage(),e);
		} finally {
			closeConnections();
		}
		return actualizado;
	}	
	
	public int insertarPedidoDetalle(final int idPedido,final int idProducto,final int cantidad) throws DatoException {
		int idPedidoDetalleInsertado = 0;
		final String METHOD_NAME = "[insertarPedidoDetalle]";
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		try {

			StringBuffer INSERCION = new StringBuffer();
			
			INSERCION.append(" INSERT INTO pedido_detalle(idpedido,idproducto,cantidad) VALUES(?,?,?); ");
			
			logger.info(INSERCION.toString());
			
			conexion = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(INSERCION.toString(),Statement.RETURN_GENERATED_KEYS);
		
			preparedStatement.setInt(1, idPedido);
			preparedStatement.setInt(2, idProducto);
			preparedStatement.setInt(3, cantidad);
			
			idPedidoDetalleInsertado = idPedidoDetalleInsertado + preparedStatement.executeUpdate();
			
			if(idPedidoDetalleInsertado==0){
				throw new DatoException("No fue posible registrar al nuevo pedido.");
			}else{
				resultSet = preparedStatement.getGeneratedKeys();
				resultSet.next();
				idPedidoDetalleInsertado = resultSet.getInt(1);
			}
			
			logger.info("estatus:["+idPedidoDetalleInsertado+"]");
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DatoException(e.getMessage(),e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatoException(e.getMessage(),e);
		} finally {
			closeConnections();
		}
		return idPedidoDetalleInsertado;
	}

	
	public Pedido consultarUltimoPedidoCreadoPorIdUsuarioEstadoPedido(final int idUsuario,final int idEstadoPedido,final int idCliente) throws DatoException{
		
		final String METHOD_NAME = "[consultarUltimoPedidoCreadoPorIdUsuarioEstadoPedido]";
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		Pedido pedido = null;
		StringBuffer CONSULTA = new StringBuffer();
		
		CONSULTA.append(" SELECT idpedido,idpedidoestado,idcliente,idusuario,observacion ");
		CONSULTA.append(" FROM pedido ");
		CONSULTA.append(" WHERE idusuario=? ");
		CONSULTA.append(" AND idpedidoestado=? AND idcliente=?");
		
		logger.info(CONSULTA.toString());
		
		try{
			conexion          = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(CONSULTA.toString());
			preparedStatement.setInt(1, idUsuario);
			preparedStatement.setInt(2, idEstadoPedido);
			preparedStatement.setInt(3, idCliente);
			resultSet         = preparedStatement.executeQuery();
						
			if(resultSet.next()){
				pedido =  new Pedido();
				pedido.setIdpedido(resultSet.getInt("idpedido"));
				pedido.setIdpedidoestado(resultSet.getInt("idpedidoestado"));
				pedido.setIdcliente(resultSet.getInt("idcliente"));
				pedido.setIdusuario(resultSet.getInt("idusuario"));
				pedido.setObservacion(resultSet.getString("observacion"));
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
		
		return pedido;
	}
	
	
	public List<Carrito> consultarCarritoDetalle(final int idPedido) throws DatoException{
		
		final String METHOD_NAME    = "[consultarCarritoDetalle]";
		List<Carrito> listaCarrito  = new ArrayList<>();
		Carrito carrito             = null;
		PedidoDetalle pedidoDetalle = null;
		Producto producto           = null;
						
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		StringBuffer CONSULTA = new StringBuffer();
		
		CONSULTA.append(" SELECT ");
		CONSULTA.append(" pd.idpedidodetalle,pd.idpedido,pd.idproducto,pd.cantidad, ");
		CONSULTA.append(" p.idproducto,p.idproductoestado,p.idproductocategoria,p.sku,p.codigobarras, ");
		CONSULTA.append(" p.nombre,p.descripcion,p.preciounitario,p.preciodescuento,p.cantidad ");
		CONSULTA.append(" FROM pedido_detalle pd,producto p ");
		CONSULTA.append(" WHERE pd.idproducto=p.idproducto ");
		CONSULTA.append(" AND pd.idpedido=? ");
				
		logger.info(CONSULTA.toString());
		
		try{
			conexion          = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(CONSULTA.toString());
			preparedStatement.setInt(1,idPedido);
			resultSet         = preparedStatement.executeQuery();
						
			while(resultSet.next()){
				
				pedidoDetalle = new PedidoDetalle();
				producto      = new Producto();
				carrito       = new Carrito();
				
				pedidoDetalle.setIdpedidodetalle(resultSet.getInt("idpedidodetalle"));
				pedidoDetalle.setIdpedido(resultSet.getInt("idpedido"));
				pedidoDetalle.setIdproducto(resultSet.getInt("idproducto"));
				pedidoDetalle.setCantidad(resultSet.getInt("cantidad"));
								
				producto.setIdProducto(resultSet.getInt("idproducto"));
				producto.setIdProductoEstado(resultSet.getInt("idproductoestado"));
				producto.setIdProductoCategoria(resultSet.getInt("idproductocategoria"));
				producto.setSku(resultSet.getString("sku"));
				producto.setCodigoBarras(resultSet.getString("codigobarras"));
				producto.setNombre(resultSet.getString("nombre"));
				producto.setDescripcion(resultSet.getString("descripcion"));
				producto.setPrecioUnitario(resultSet.getInt("preciounitario"));
				producto.setPrecioDescuento(resultSet.getInt("preciodescuento"));
				producto.setCantidad(resultSet.getInt("cantidad"));
				
				carrito.setPedidoDetalle(pedidoDetalle);
				carrito.setProducto(producto);
				
				listaCarrito.add(carrito);
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
		
		return listaCarrito;
	}
	
	
	public List<MiPedido> consultarPedidosPorUsuario(final int idUsuario) throws DatoException{
		
		final String METHOD_NAME   = "[consultarPedidosPorUsuario]";
		List<MiPedido> misPedidos  = new ArrayList<>();
		MiPedido miPedido          = null;
		Pedido pedido              = null;
		Cliente cliente            = null;
						
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		StringBuffer CONSULTA = new StringBuffer();
		
		CONSULTA.append(" SELECT ");
		CONSULTA.append(" pe.idpedido,pe.idpedidoestado,pe.idcliente,pe.idusuario,pe.observacion, ");
		CONSULTA.append(" c.idcliente, c.idtipodocumento, c.numerodocumento, c.nombre, c.apellido, c.direccion, c.telefono, c.estado ");
		CONSULTA.append(" FROM pedido pe,cliente c ");
		CONSULTA.append(" WHERE pe.idusuario=? ");
		CONSULTA.append(" AND pe.idpedidoestado=? ");
		CONSULTA.append(" AND pe.idcliente=c.idcliente ");
				
		logger.info(CONSULTA.toString());
		
		try{
			conexion          = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(CONSULTA.toString());
			preparedStatement.setInt(1,idUsuario);
			preparedStatement.setInt(2,EstadoPedidoEnum.EN_VENTA.getIndex());
			resultSet         = preparedStatement.executeQuery();
						
			while(resultSet.next()){
				miPedido = new MiPedido();
				pedido   = new Pedido();
				cliente  = new Cliente();
				
				pedido.setIdpedido(resultSet.getInt("idpedido"));
				pedido.setIdpedidoestado(resultSet.getInt("idpedidoestado"));
				pedido.setIdcliente(resultSet.getInt("idcliente"));
				pedido.setIdusuario(resultSet.getInt("idusuario"));
				pedido.setObservacion(resultSet.getString("observacion"));
				
				cliente.setIdcliente(resultSet.getInt("idcliente"));
				cliente.setIdTipoDocumento(resultSet.getInt("idtipodocumento"));
				cliente.setNumeroDocumento(resultSet.getString("numerodocumento"));
				cliente.setNombre(resultSet.getString("nombre"));
				cliente.setApellido(resultSet.getString("apellido"));
				cliente.setDireccion(resultSet.getString("direccion"));
				cliente.setTelefono(resultSet.getString("telefono"));
				cliente.setEstado(resultSet.getInt("estado"));
				
				miPedido.setPedido(pedido);
				miPedido.setCliente(cliente);
				
				misPedidos.add(miPedido);
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
		
		return misPedidos;
	}
	
	
}
