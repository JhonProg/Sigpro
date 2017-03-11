package co.com.sigpro.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import co.com.sigpro.bean.ProductoCategoria;
import co.com.sigpro.bean.ProductoEstado;
import co.com.sigpro.bean.Rol;
import co.com.sigpro.bean.TipoDocumento;
import co.com.sigpro.constante.EstadoEnum;
import co.com.sigpro.exception.DatoException;
import co.com.sigpro.util.Log;


/**
 * Clase para manejo de operaciones con los catalogos
 * */
public class CatalogoDAO extends DataBaseDAO{

	private Log logger = new Log();
	
	private final String CLASS_NAME = "[CatalogoDAO]";
	
	/**
	 * Constructor.
	 * */
	public CatalogoDAO(){
	}
	
	/**
	 * Constructor. Recibe la conexion a la fuente de datos.
	 * @param DataSource dataSource
	 * */
	public CatalogoDAO(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	/**
	 * Consulta el catalogo de los tipos de documento
	 * @throws DataException 
	 * */
	public List<TipoDocumento> consultarTiposDocumento() throws DatoException{
				
		final String METHOD_NAME = "[consultarTiposDocumento]";
		
		List<TipoDocumento> listaTiposDocumento = new ArrayList<>();
		TipoDocumento tipoDocumento = null;
		
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		StringBuffer CONSULTA = new StringBuffer();
		
		CONSULTA.append("SELECT idtipodocumento,tipo,descripcion,estado FROM tipo_documento WHERE estado=?");
		
		logger.info(CONSULTA.toString());
		
		try{
			conexion          = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(CONSULTA.toString());
			preparedStatement.setInt(1, EstadoEnum.ACTIVO.getIndex());
			resultSet         = preparedStatement.executeQuery();
						
			while(resultSet.next()){
				tipoDocumento = new TipoDocumento();
				
				tipoDocumento.setIdTipoDocumento(resultSet.getInt("idtipodocumento"));
				tipoDocumento.setTipo(resultSet.getString("tipo"));
				tipoDocumento.setDescripcion(resultSet.getString("descripcion"));
				tipoDocumento.setEstado(resultSet.getInt("estado"));
				
				listaTiposDocumento.add(tipoDocumento);
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
		
		return listaTiposDocumento;
	}
	
	
	/**
	 * Consulta el catalogo de los roles
	 * @throws DataException 
	 * */
	public List<Rol> consultarRoles() throws DatoException{
				
		final String METHOD_NAME = "[consultarRoles]";
		
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		List<Rol> listaRoles  = new ArrayList<>();
		Rol rol               = null;
		StringBuffer CONSULTA = new StringBuffer();
		
		CONSULTA.append("SELECT idrol,rol,descripcion,estado FROM rol WHERE estado=?");
		
		logger.info(CONSULTA.toString());
		
		try{
			conexion          = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(CONSULTA.toString());
			preparedStatement.setInt(1, EstadoEnum.ACTIVO.getIndex());
			resultSet         = preparedStatement.executeQuery();
						
			while(resultSet.next()){
				rol = new Rol();
				
				rol.setIdRol(resultSet.getInt("idrol"));
				rol.setRol(resultSet.getString("rol"));
				rol.setDescripcion(resultSet.getString("descripcion"));
				rol.setEstado(resultSet.getInt("estado"));
				
				listaRoles.add(rol);
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
		
		return listaRoles;
	}
	
	
	public List<ProductoCategoria> consultarProductoCategorias() throws DatoException{
		final String METHOD_NAME = "[consultarProductoCategorias]";
		
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		List<ProductoCategoria> categorias  = new ArrayList<>();
		ProductoCategoria productoCategoria = null;
		StringBuffer CONSULTA = new StringBuffer();
		
		CONSULTA.append("SELECT idproductocategoria,categoria,descripcion,estado FROM producto_categoria ");
		
		logger.info(CONSULTA.toString());
		
		try{
			conexion          = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(CONSULTA.toString());
			resultSet         = preparedStatement.executeQuery();
						
			while(resultSet.next()){
				productoCategoria = new ProductoCategoria();
				
				productoCategoria.setIdProductoCategoria(resultSet.getInt("idproductocategoria"));
				productoCategoria.setCategoria(resultSet.getString("categoria"));
				productoCategoria.setDescripcion(resultSet.getString("descripcion"));
				productoCategoria.setEstado(resultSet.getInt("estado"));
				
				categorias.add(productoCategoria);
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
		
		return categorias;
	}
	
	

	public List<ProductoEstado> consultarProductoEstados() throws DatoException{
		final String METHOD_NAME = "[consultarProductoEstados]";
		
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		List<ProductoEstado> estados  = new ArrayList<>();
		ProductoEstado productoEstado = null;
		StringBuffer CONSULTA = new StringBuffer();
		
		CONSULTA.append(" SELECT idproductoestado, estado, descripcion FROM producto_estado ");
		
		logger.info(CONSULTA.toString());
		
		try{
			conexion          = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(CONSULTA.toString());
			resultSet         = preparedStatement.executeQuery();
						
			while(resultSet.next()){
				productoEstado = new ProductoEstado();
				
				productoEstado.setIdpedidoestado(resultSet.getInt("idproductoestado"));
				productoEstado.setEstado(resultSet.getString("estado"));
				productoEstado.setDescripcion(resultSet.getString("descripcion"));
								
				estados.add(productoEstado);
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
		
		return estados;
	}
	
}
