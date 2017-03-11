package co.com.sigpro.ejb;

import java.util.List;

import javax.ejb.Stateless;

import co.com.sigpro.bean.ProductoCategoria;
import co.com.sigpro.bean.ProductoEstado;
import co.com.sigpro.bean.Rol;
import co.com.sigpro.bean.TipoDocumento;
import co.com.sigpro.dao.CatalogoDAO;
import co.com.sigpro.exception.DatoException;
import co.com.sigpro.exception.LogicaException;
import co.com.sigpro.util.Log;

@Stateless
public class CatalogoEJB extends DataBaseEJB{

	private Log logger = new Log();
	
	/**
	 * Consulta el catalogo de los tipos de documento
	 * @throws DataException 
	 * */
	public List<TipoDocumento> consultarTiposDocumento() throws LogicaException{
		try{
			CatalogoDAO catalogoDAO = new CatalogoDAO(ds_consulta);
			return catalogoDAO.consultarTiposDocumento();
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	}
	
	
	/**
	 * Consulta el catalogo de los roles
	 * @throws DataException 
	 * */
	public List<Rol> consultarRoles() throws LogicaException{
		try{
			CatalogoDAO catalogoDAO = new CatalogoDAO(ds_consulta);
			return catalogoDAO.consultarRoles();
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	}
	
	/**
	 * Consulta el catalogo de las categorias.
	 * @throws DataException 
	 * */
	public List<ProductoCategoria> consultarProductoCategorias() throws LogicaException{
		try{
			CatalogoDAO catalogoDAO = new CatalogoDAO(ds_consulta);
			return catalogoDAO.consultarProductoCategorias();
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	}
	
	
	
	/**
	 * Consulta el catalogo de las categorias.
	 * @throws DataException 
	 * */
	public List<ProductoEstado> consultarProductoEstados() throws LogicaException{
		try{
			CatalogoDAO catalogoDAO = new CatalogoDAO(ds_consulta);
			return catalogoDAO.consultarProductoEstados();
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	}
	
}











