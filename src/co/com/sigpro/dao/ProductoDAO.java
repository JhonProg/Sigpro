package co.com.sigpro.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import co.com.sigpro.bean.Producto;
import co.com.sigpro.bean.ProductoCategoria;
import co.com.sigpro.bean.ProductoEstado;
import co.com.sigpro.exception.DatoException;
import co.com.sigpro.util.Log;

public class ProductoDAO extends DataBaseDAO{

	private Log logger = new Log();
	
	private final String CLASS_NAME = "[ProductoDAO]";
	
	/**
	 * Constructor.
	 * */
	public ProductoDAO(){
	}
	
	/**
	 * Constructor. Recibe la conexion a la fuente de datos.
	 * @param DataSource dataSource
	 * */
	public ProductoDAO(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	
	public List<Producto> consultarProductosPorNombre(final String nombre) throws DatoException{
		final String METHOD_NAME = "[consultarProductosPorNombre]";
		
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		List<Producto> productos            = new ArrayList<>();
		StringBuffer CONSULTA               = new StringBuffer();
		
		Producto producto                   = null;
		ProductoEstado productoEstado       = null;
		ProductoCategoria productoCategoria = null;
		
		
		CONSULTA.append(" SELECT p.idproducto, p.idproductoestado, p.idproductocategoria, p.sku, p.codigobarras, p.nombre, p.descripcion, p.preciounitario, p.preciodescuento, p.cantidad ");
		CONSULTA.append(" ,pe.idproductoestado, pe.estado, pe.descripcion ");
		CONSULTA.append(" ,pc.idproductocategoria, pc.categoria, pc.descripcion, pc.estado ");
		CONSULTA.append(" FROM producto p,producto_estado pe,producto_categoria pc ");
		CONSULTA.append(" WHERE p.idproductoestado  = pe.idproductoestado ");
		CONSULTA.append(" AND p.idproductocategoria = pc.idproductocategoria ");
		CONSULTA.append(" AND p.nombre ilike '%"+nombre+"%' ");
		
		logger.info(CONSULTA.toString());
		
		try{
			conexion          = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(CONSULTA.toString());
			resultSet         = preparedStatement.executeQuery();
			
			int columna = 1;
			
			while(resultSet.next()){
				columna = 1;
				
				producto = new Producto();
				
				producto.setIdProducto(resultSet.getInt(columna++));
				producto.setIdProductoEstado(resultSet.getInt(columna++));
				producto.setIdProductoCategoria(resultSet.getInt(columna++));
				producto.setSku(resultSet.getString(columna++));
				producto.setCodigoBarras(resultSet.getString(columna++));
				producto.setNombre(resultSet.getString(columna++));
				producto.setDescripcion(resultSet.getString(columna++));
				producto.setPrecioUnitario(resultSet.getInt(columna++));
				producto.setPrecioDescuento(resultSet.getInt(columna++));
				producto.setCantidad(resultSet.getInt(columna++));
				
				productoEstado = new ProductoEstado();
				
				productoEstado.setIdpedidoestado(resultSet.getInt(columna++));
				productoEstado.setEstado(resultSet.getString(columna++));
				productoEstado.setDescripcion(resultSet.getString(columna++));
				
				productoCategoria = new ProductoCategoria();
				
				productoCategoria.setIdProductoCategoria(resultSet.getInt(columna++));
				productoCategoria.setCategoria(resultSet.getString(columna++));
				productoCategoria.setDescripcion(resultSet.getString(columna++));
				productoCategoria.setEstado(resultSet.getInt(columna++));
				
				producto.setProductoEstado(productoEstado);
				producto.setProductoCategoria(productoCategoria);
								
				productos.add(producto);
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
		
		return productos;
	}
	
	
	
	public List<Producto> consultarProductosPorCategoria(final Integer idCategoriaProducto) throws DatoException{
		final String METHOD_NAME = "[consultarProductosPorCategoria]";
		
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		List<Producto> productos            = new ArrayList<>();
		StringBuffer CONSULTA               = new StringBuffer();
		
		Producto producto                   = null;
		ProductoEstado productoEstado       = null;
		ProductoCategoria productoCategoria = null;
		
		
		CONSULTA.append(" SELECT p.idproducto, p.idproductoestado, p.idproductocategoria, p.sku, p.codigobarras, p.nombre, p.descripcion, p.preciounitario, p.preciodescuento, p.cantidad ");
		CONSULTA.append(" ,pe.idproductoestado, pe.estado, pe.descripcion ");
		CONSULTA.append(" ,pc.idproductocategoria, pc.categoria, pc.descripcion, pc.estado ");
		CONSULTA.append(" FROM producto p,producto_estado pe,producto_categoria pc ");
		CONSULTA.append(" WHERE p.idproductoestado  = pe.idproductoestado ");
		CONSULTA.append(" AND p.idproductocategoria = pc.idproductocategoria ");
		CONSULTA.append(" AND p.idproductocategoria = ? ");
		
		logger.info(CONSULTA.toString());
		
		try{
			conexion          = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(CONSULTA.toString());
			preparedStatement.setInt(1, idCategoriaProducto);
			resultSet         = preparedStatement.executeQuery();
			
			int columna = 1;
			
			while(resultSet.next()){
				columna = 1;
				
				producto = new Producto();
				
				producto.setIdProducto(resultSet.getInt(columna++));
				producto.setIdProductoEstado(resultSet.getInt(columna++));
				producto.setIdProductoCategoria(resultSet.getInt(columna++));
				producto.setSku(resultSet.getString(columna++));
				producto.setCodigoBarras(resultSet.getString(columna++));
				producto.setNombre(resultSet.getString(columna++));
				producto.setDescripcion(resultSet.getString(columna++));
				producto.setPrecioUnitario(resultSet.getInt(columna++));
				producto.setPrecioDescuento(resultSet.getInt(columna++));
				producto.setCantidad(resultSet.getInt(columna++));
				
				productoEstado = new ProductoEstado();
				
				productoEstado.setIdpedidoestado(resultSet.getInt(columna++));
				productoEstado.setEstado(resultSet.getString(columna++));
				productoEstado.setDescripcion(resultSet.getString(columna++));
				
				productoCategoria = new ProductoCategoria();
				
				productoCategoria.setIdProductoCategoria(resultSet.getInt(columna++));
				productoCategoria.setCategoria(resultSet.getString(columna++));
				productoCategoria.setDescripcion(resultSet.getString(columna++));
				productoCategoria.setEstado(resultSet.getInt(columna++));
				
				producto.setProductoEstado(productoEstado);
				producto.setProductoCategoria(productoCategoria);
								
				productos.add(producto);
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
		
		return productos;
	}

		
	public int insertarProducto(Producto nuevoProducto) throws DatoException {
		Integer estatus = 0;
		int columna = 1;

		try {

			StringBuffer INSERCION = new StringBuffer();
			
			INSERCION.append(" INSERT INTO producto(idproductoestado,idproductocategoria,sku,codigobarras,nombre,descripcion,preciounitario,preciodescuento,cantidad) ");
			INSERCION.append(" VALUES(?,?,?,?,?,?,?,?,?) ");
						
			conexion = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(INSERCION.toString(),Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setInt(columna++,nuevoProducto.getIdProductoEstado());
			preparedStatement.setInt(columna++,nuevoProducto.getIdProductoCategoria());
			preparedStatement.setString(columna++,nuevoProducto.getSku());
			preparedStatement.setString(columna++,nuevoProducto.getCodigoBarras());
			preparedStatement.setString(columna++,nuevoProducto.getNombre());
			preparedStatement.setString(columna++,nuevoProducto.getDescripcion());
			preparedStatement.setInt(columna++,nuevoProducto.getPrecioUnitario());
			preparedStatement.setInt(columna++,nuevoProducto.getPrecioDescuento());
			preparedStatement.setInt(columna++,nuevoProducto.getCantidad());

			estatus = estatus + preparedStatement.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DatoException(e.getMessage(),e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatoException(e.getMessage(),e);
		} finally {
			closeConnections();
		}
		return estatus;

	}
	
	
	
	public Producto consultarProductoPorIdProducto(final Integer idProducto) throws DatoException{
		final String METHOD_NAME = "[consultarProductoPorIdProducto]";
		
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		StringBuffer CONSULTA               = new StringBuffer();
		Producto producto                   = null;
		ProductoEstado productoEstado       = null;
		ProductoCategoria productoCategoria = null;
		
		
		CONSULTA.append(" SELECT p.idproducto, p.idproductoestado, p.idproductocategoria, p.sku, p.codigobarras, p.nombre, p.descripcion, p.preciounitario, p.preciodescuento, p.cantidad ");
		CONSULTA.append(" ,pe.idproductoestado, pe.estado, pe.descripcion ");
		CONSULTA.append(" ,pc.idproductocategoria, pc.categoria, pc.descripcion, pc.estado ");
		CONSULTA.append(" FROM producto p,producto_estado pe,producto_categoria pc ");
		CONSULTA.append(" WHERE p.idproductoestado  = pe.idproductoestado ");
		CONSULTA.append(" AND p.idproductocategoria = pc.idproductocategoria ");
		CONSULTA.append(" AND p.idproducto = ? ");
		
		logger.info(CONSULTA.toString());
		
		try{
			conexion          = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(CONSULTA.toString());
			preparedStatement.setInt(1, idProducto);
			resultSet         = preparedStatement.executeQuery();
			
			int columna = 1;
			
			if(resultSet.next()){
				columna = 1;
				
				producto = new Producto();
				
				producto.setIdProducto(resultSet.getInt(columna++));
				producto.setIdProductoEstado(resultSet.getInt(columna++));
				producto.setIdProductoCategoria(resultSet.getInt(columna++));
				producto.setSku(resultSet.getString(columna++));
				producto.setCodigoBarras(resultSet.getString(columna++));
				producto.setNombre(resultSet.getString(columna++));
				producto.setDescripcion(resultSet.getString(columna++));
				producto.setPrecioUnitario(resultSet.getInt(columna++));
				producto.setPrecioDescuento(resultSet.getInt(columna++));
				producto.setCantidad(resultSet.getInt(columna++));
				
				productoEstado = new ProductoEstado();
				
				productoEstado.setIdpedidoestado(resultSet.getInt(columna++));
				productoEstado.setEstado(resultSet.getString(columna++));
				productoEstado.setDescripcion(resultSet.getString(columna++));
				
				productoCategoria = new ProductoCategoria();
				
				productoCategoria.setIdProductoCategoria(resultSet.getInt(columna++));
				productoCategoria.setCategoria(resultSet.getString(columna++));
				productoCategoria.setDescripcion(resultSet.getString(columna++));
				productoCategoria.setEstado(resultSet.getInt(columna++));
				
				producto.setProductoEstado(productoEstado);
				producto.setProductoCategoria(productoCategoria);
			
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
		
		return producto;
	}

	
	public int consultarCantidadDisponibleDeUnProducto(final Integer idProducto) throws DatoException{
		final String METHOD_NAME = "[consultarCantidadDisponibleDeUnProducto]";
		
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		StringBuffer CONSULTA = new StringBuffer();
		int cantidadProducto  = 0;		
		
		CONSULTA.append(" SELECT cantidad FROM producto WHERE idproducto=?");
				
		logger.info(CONSULTA.toString());
		
		try{
			conexion          = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(CONSULTA.toString());
			preparedStatement.setInt(1, idProducto);
			resultSet         = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				cantidadProducto = resultSet.getInt("cantidad");
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
		
		return cantidadProducto;
	}
	
	public int editarProducto(final Producto productoEdicion) throws DatoException {
		Integer estatus = 0;
		int columna = 1;

		try {

			StringBuffer ACTUALIZACION = new StringBuffer();
			
			ACTUALIZACION.append(" UPDATE producto ");
			ACTUALIZACION.append(" SET  idproductoestado=?, idproductocategoria=?, sku=?,codigobarras=?, nombre=?, descripcion=?, preciounitario=?, preciodescuento=?, cantidad=?  ");
			ACTUALIZACION.append(" WHERE idproducto=?  ");
			
			conexion = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(ACTUALIZACION.toString());
			columna = 1;
						
			preparedStatement.setInt(columna++,productoEdicion.getIdProductoEstado());
			preparedStatement.setInt(columna++,productoEdicion.getIdProductoCategoria());
			preparedStatement.setString(columna++,productoEdicion.getSku());
			preparedStatement.setString(columna++,productoEdicion.getCodigoBarras());
			preparedStatement.setString(columna++,productoEdicion.getNombre());
			preparedStatement.setString(columna++,productoEdicion.getDescripcion());
			preparedStatement.setInt(columna++,productoEdicion.getPrecioUnitario());
			preparedStatement.setInt(columna++,productoEdicion.getPrecioDescuento());
			preparedStatement.setInt(columna++,productoEdicion.getCantidad());
			preparedStatement.setInt(columna++,productoEdicion.getIdProducto());
			
			estatus = estatus + preparedStatement.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DatoException(e.getMessage(),e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatoException(e.getMessage(),e);
		} finally {
			closeConnections();
		}
		return estatus;

	}
	
	
}
