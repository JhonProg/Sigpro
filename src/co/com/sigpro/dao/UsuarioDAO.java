package co.com.sigpro.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import co.com.sigpro.bean.Rol;
import co.com.sigpro.bean.TipoDocumento;
import co.com.sigpro.bean.Usuario;
import co.com.sigpro.exception.DatoException;
import co.com.sigpro.util.Log;


/**
 * Clase para manejo de operaciones con los catalogos
 * */
public class UsuarioDAO extends DataBaseDAO{

	private Log logger = new Log();
	
	private final String CLASS_NAME = "[UsuarioDAO]";
	
	/**
	 * Constructor.
	 * */
	public UsuarioDAO(){
	}
	
	/**
	 * Constructor. Recibe la conexion a la fuente de datos.
	 * @param DataSource dataSource
	 * */
	public UsuarioDAO(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	/**
	 * Consulta usuarios por tipo y numero de identificacion.
	 * @throws DataException 
	 * */
	public List<Usuario> consultarUsuariosPorTipoNumeroIdentificacion(final Integer idTipoDocumento,final String numeroIdentificacion) throws DatoException{
				
		final String METHOD_NAME = "[consultarUsuariosPorTipoNumeroIdentificacion]";
		List<Usuario> usuarios   = new ArrayList<>();
		Usuario usuario          = null;
				
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		StringBuffer CONSULTA = new StringBuffer();
		
		CONSULTA.append(" SELECT ");
		CONSULTA.append(" u.idusuario,u.idtipodocumento,u.idrol,u.numerodocumento,u.usuario,u.clave,u.nombre,u.apellido,u.estado, ");
		CONSULTA.append(" t.idtipodocumento,t.tipo,t.descripcion,t.estado, ");
		CONSULTA.append(" r.idrol,r.rol,r.descripcion,r.estado ");
		CONSULTA.append(" FROM ");
		CONSULTA.append(" usuario u,tipo_documento t,rol r ");
		CONSULTA.append(" WHERE ");
		CONSULTA.append(" u.idtipodocumento=t.idtipodocumento ");
		CONSULTA.append(" AND u.idrol = r.idrol ");
		CONSULTA.append(" AND u.idtipodocumento = ? ");
		CONSULTA.append(" AND u.numerodocumento like '%"+numeroIdentificacion+"%'");
				
		logger.info(CONSULTA.toString());
		
		try{
			conexion          = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(CONSULTA.toString());
			preparedStatement.setInt(1,idTipoDocumento);
			resultSet         = preparedStatement.executeQuery();
			int columna       = 1;
			
			while(resultSet.next()){
				columna = 1;
				
				usuario = new Usuario();
				
				usuario.setIdUsuario(resultSet.getInt(columna++));
				usuario.setIdTipoDocumento(resultSet.getInt(columna++));
				usuario.setIdRol(resultSet.getInt(columna++));
				usuario.setNumeroDocumento(resultSet.getString(columna++));
				usuario.setUsuario(resultSet.getString(columna++));
				usuario.setClave(resultSet.getString(columna++));
				usuario.setNombre(resultSet.getString(columna++));
				usuario.setApellido(resultSet.getString(columna++));
				usuario.setEstado(resultSet.getInt(columna++));
				
				TipoDocumento tipoDocumento = new TipoDocumento();
				
				tipoDocumento.setIdTipoDocumento(resultSet.getInt(columna++));
				tipoDocumento.setTipo(resultSet.getString(columna++));
				tipoDocumento.setDescripcion(resultSet.getString(columna++));
				tipoDocumento.setEstado(resultSet.getInt(columna++));
				
				Rol rol = new Rol();
				
				rol.setIdRol(resultSet.getInt(columna++));
				rol.setRol(resultSet.getString(columna++));
				rol.setDescripcion(resultSet.getString(columna++));
				rol.setEstado(resultSet.getInt(columna++));
				
				usuario.setTipoDocumento(tipoDocumento);
				usuario.setRol(rol);
								
				usuarios.add(usuario);
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
		
		return usuarios;
	}
	
	
	
	/**
	 * Consulta usuarios por nombre
	 * @throws DataException 
	 * */
	public List<Usuario> consultarUsuariosPorNombre(final String nombre) throws DatoException{
				
		final String METHOD_NAME = "[consultarUsuariosPorNombre]";
		List<Usuario> usuarios   = new ArrayList<>();
		Usuario usuario          = null;
				
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		StringBuffer CONSULTA = new StringBuffer();
		
		CONSULTA.append(" SELECT \r\n");
		CONSULTA.append(" u.idusuario,u.idtipodocumento,u.idrol,u.numerodocumento,u.usuario,u.clave,u.nombre,u.apellido,u.estado, \r\n");
		CONSULTA.append(" t.idtipodocumento,t.tipo,t.descripcion,t.estado, \r\n");
		CONSULTA.append(" r.idrol,r.rol,r.descripcion,r.estado \r\n");
		CONSULTA.append(" FROM \r\n");
		CONSULTA.append(" usuario u,tipo_documento t,rol r \r\n");
		CONSULTA.append(" WHERE \r\n");
		CONSULTA.append(" u.idtipodocumento=t.idtipodocumento \r\n");
		CONSULTA.append(" AND u.idrol = r.idrol \r\n");
		CONSULTA.append(" AND (u.nombre ILIKE '%"+nombre+"%' OR u.apellido ILIKE '%"+nombre+"%') \r\n");
				
		logger.info(CONSULTA.toString());
		
		try{
			conexion          = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(CONSULTA.toString());
			resultSet         = preparedStatement.executeQuery();
			int columna       = 1;
			
			while(resultSet.next()){
				columna = 1;
				
				usuario = new Usuario();
				
				usuario.setIdUsuario(resultSet.getInt(columna++));
				usuario.setIdTipoDocumento(resultSet.getInt(columna++));
				usuario.setIdRol(resultSet.getInt(columna++));
				usuario.setNumeroDocumento(resultSet.getString(columna++));
				usuario.setUsuario(resultSet.getString(columna++));
				usuario.setClave(resultSet.getString(columna++));
				usuario.setNombre(resultSet.getString(columna++));
				usuario.setApellido(resultSet.getString(columna++));
				usuario.setEstado(resultSet.getInt(columna++));
				
				TipoDocumento tipoDocumento = new TipoDocumento();
				
				tipoDocumento.setIdTipoDocumento(resultSet.getInt(columna++));
				tipoDocumento.setTipo(resultSet.getString(columna++));
				tipoDocumento.setDescripcion(resultSet.getString(columna++));
				tipoDocumento.setEstado(resultSet.getInt(columna++));
				
				Rol rol = new Rol();
				
				rol.setIdRol(resultSet.getInt(columna++));
				rol.setRol(resultSet.getString(columna++));
				rol.setDescripcion(resultSet.getString(columna++));
				rol.setEstado(resultSet.getInt(columna++));
				
				usuario.setTipoDocumento(tipoDocumento);
				usuario.setRol(rol);
								
				usuarios.add(usuario);
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
		
		return usuarios;
	}
	
	
	
	
	public int insertarUsuario(final Usuario nuevoUsuario) throws DatoException {
		Integer estatus = 0;
		int columna = 1;

		try {

			StringBuffer INSERCION = new StringBuffer();
			
			INSERCION.append(" INSERT INTO usuario(idtipodocumento,idrol,numerodocumento,usuario,clave,nombre,apellido,estado) ");
			INSERCION.append(" VALUES(?,?,?,?,?,?,?,?) ");
						
			conexion = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(INSERCION.toString(),Statement.RETURN_GENERATED_KEYS);
			columna = 1;

			preparedStatement.setInt(columna++,nuevoUsuario.getIdTipoDocumento());
			preparedStatement.setInt(columna++,nuevoUsuario.getIdRol());
			preparedStatement.setString(columna++,nuevoUsuario.getNumeroDocumento());
			preparedStatement.setString(columna++,nuevoUsuario.getUsuario());
			preparedStatement.setString(columna++,nuevoUsuario.getClave());
			preparedStatement.setString(columna++,nuevoUsuario.getNombre());
			preparedStatement.setString(columna++,nuevoUsuario.getApellido());
			preparedStatement.setInt(columna++,nuevoUsuario.getEstado());

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
	
	public Usuario consultarUsuarioPorIdUsuario(final Integer idUsuario) throws DatoException{
		final String METHOD_NAME = "[consultarUsuarioPorIdUsuario]";
		Usuario usuario          = null;
				
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		StringBuffer CONSULTA = new StringBuffer();
		
		CONSULTA.append(" SELECT \r\n");
		CONSULTA.append(" u.idusuario,u.idtipodocumento,u.idrol,u.numerodocumento,u.usuario,u.clave,u.nombre,u.apellido,u.estado \r\n");
		CONSULTA.append(" FROM usuario u WHERE u.idusuario=?\r\n");
				
		logger.info(CONSULTA.toString());
		
		try{
			conexion          = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(CONSULTA.toString());
			preparedStatement.setInt(1, idUsuario);
			resultSet         = preparedStatement.executeQuery();
			int columna       = 1;
			
			while(resultSet.next()){
				columna = 1;
				
				usuario = new Usuario();
				
				usuario.setIdUsuario(resultSet.getInt(columna++));
				usuario.setIdTipoDocumento(resultSet.getInt(columna++));
				usuario.setIdRol(resultSet.getInt(columna++));
				usuario.setNumeroDocumento(resultSet.getString(columna++));
				usuario.setUsuario(resultSet.getString(columna++));
				usuario.setClave(resultSet.getString(columna++));
				usuario.setNombre(resultSet.getString(columna++));
				usuario.setApellido(resultSet.getString(columna++));
				usuario.setEstado(resultSet.getInt(columna++));
				
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
		
		return usuario;
	}
	
	
	
	public int editarUsuario(final Usuario usuarioEdicion) throws DatoException {
		Integer estatus = 0;
		int columna = 1;

		try {

			StringBuffer ACTUALIZACION = new StringBuffer();
			
			ACTUALIZACION.append(" UPDATE usuario SET idtipodocumento=?,idrol=?,numerodocumento=?,usuario=?,clave=?,nombre=?,apellido=?,estado=? ");
			ACTUALIZACION.append(" WHERE idusuario=? ");
						
			conexion = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(ACTUALIZACION.toString());
			columna = 1;
						
			preparedStatement.setInt(columna++,usuarioEdicion.getIdTipoDocumento());
			preparedStatement.setInt(columna++,usuarioEdicion.getIdRol());
			preparedStatement.setString(columna++,usuarioEdicion.getNumeroDocumento());
			preparedStatement.setString(columna++,usuarioEdicion.getUsuario());
			preparedStatement.setString(columna++,usuarioEdicion.getClave());
			preparedStatement.setString(columna++,usuarioEdicion.getNombre());
			preparedStatement.setString(columna++,usuarioEdicion.getApellido());
			preparedStatement.setInt(columna++,usuarioEdicion.getEstado());
			preparedStatement.setInt(columna++,usuarioEdicion.getIdUsuario());
			
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
