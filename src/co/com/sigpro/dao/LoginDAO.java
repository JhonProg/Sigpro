package co.com.sigpro.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import co.com.sigpro.bean.Usuario;
import co.com.sigpro.constante.EstadoEnum;
import co.com.sigpro.exception.DatoException;
import co.com.sigpro.util.Log;


/**
 * Clase para manejo de operaciones con datos y acceso a los mismo.
 * */
public class LoginDAO extends DataBaseDAO{

	private Log logger = new Log();
	
	private final String CLASS_NAME = "[LoginDAO]";
	
	/**
	 * Constructor.
	 * */
	public LoginDAO(){
	}
	
	/**
	 * Constructor. Recibe la conexion a la fuente de datos.
	 * @param DataSource dataSource
	 * */
	public LoginDAO(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	/**
	 * Consulta la base de datos para validar la existencia de un usuario segun usuario y clave.
	 * @param Usuario usuario.
	 * @throws DataException 
	 * */
	public Usuario consultarUsuario(Usuario usuario) throws DatoException{
				
		final String METHOD_NAME = "[consultarUsuario]";
		Usuario usuarioExiste = null;
		
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		StringBuffer CONSULTA_USUARIO = new StringBuffer();
		
		CONSULTA_USUARIO.append(" SELECT idusuario,idtipodocumento,idrol,numerodocumento,usuario,clave,nombre,apellido,estado");
		CONSULTA_USUARIO.append(" FROM usuario WHERE usuario=? AND clave = ? AND estado=? ");
		
		logger.info(CONSULTA_USUARIO.toString());
		
		try{
			conexion = dataSource.getConnection();
			
			/** Parametros */
			preparedStatement = conexion.prepareStatement(CONSULTA_USUARIO.toString());
			preparedStatement.setString(1, usuario.getUsuario());
			preparedStatement.setString(2, usuario.getClave());
			preparedStatement.setInt(3, EstadoEnum.ACTIVO.getIndex());
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				usuarioExiste = new Usuario();
				
				usuarioExiste.setIdUsuario(resultSet.getInt("idusuario"));
				usuarioExiste.setIdTipoDocumento(resultSet.getInt("idtipodocumento"));
				usuarioExiste.setIdRol(resultSet.getInt("idrol"));
				usuarioExiste.setNumeroDocumento(resultSet.getString("numerodocumento"));
				usuarioExiste.setUsuario(resultSet.getString("usuario"));
				usuarioExiste.setClave(resultSet.getString("clave"));
				usuarioExiste.setNombre(resultSet.getString("nombre"));
				usuarioExiste.setApellido(resultSet.getString("apellido"));
				usuarioExiste.setEstado(resultSet.getInt("estado"));
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
		
		return usuarioExiste;
	}
	
}
