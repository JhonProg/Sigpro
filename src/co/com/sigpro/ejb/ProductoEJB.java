package co.com.sigpro.ejb;

import java.util.List;

import javax.ejb.Stateless;

import co.com.sigpro.bean.Producto;
import co.com.sigpro.dao.ProductoDAO;
import co.com.sigpro.exception.DatoException;
import co.com.sigpro.exception.LogicaException;
import co.com.sigpro.util.Log;

@Stateless
public class ProductoEJB extends DataBaseEJB {

	private Log logger = new Log();
	
	/**
	 * Consulta la base de datos los productos por nombre.
	 * @param String nombre del producto.
	 * @throws DataException 
	 * */
	public List<Producto> consultarProductosPorNombre(final String nombre) throws LogicaException{
		try{
			ProductoDAO productoDAO = new ProductoDAO(ds_consulta);
			return productoDAO.consultarProductosPorNombre(nombre);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	}
	
	
	
	/**
	 * Consulta la base de datos los productos segun la categoria.
	 * @param String categoria del producto.
	 * @throws DataException 
	 * */
	public List<Producto> consultarProductosPorCategoria(final Integer idProductoCategoria) throws LogicaException{
		try{
			ProductoDAO productoDAO = new ProductoDAO(ds_consulta);
			return productoDAO.consultarProductosPorCategoria(idProductoCategoria);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	}
	
	public int insertarProducto(final Producto nuevoProducto) throws LogicaException{
		try{
			ProductoDAO productoDAO = new ProductoDAO(ds_consulta);
			return productoDAO.insertarProducto(nuevoProducto);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	}
	
	
	
	public Producto consultarProductoPorIdProducto(final Integer idProducto) throws LogicaException{
		try{
			ProductoDAO productoDAO = new ProductoDAO(ds_consulta);
			return productoDAO.consultarProductoPorIdProducto(idProducto);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	}
	
	
	public int editarProducto(final Producto productoEdicion) throws LogicaException{
		try{
			ProductoDAO productoDAO = new ProductoDAO(ds_consulta);
			return productoDAO.editarProducto(productoEdicion);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	}
	
	
	
	public int consultarCantidadDisponibleDeUnProducto(final Integer idProducto) throws LogicaException{
		try{
			ProductoDAO productoDAO = new ProductoDAO(ds_consulta);
			return productoDAO.consultarCantidadDisponibleDeUnProducto(idProducto);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	}
}
