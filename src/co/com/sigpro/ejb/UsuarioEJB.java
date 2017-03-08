package co.com.sigpro.ejb;

import java.util.List;

import javax.ejb.Stateless;

import co.com.sigpro.bean.Usuario;
import co.com.sigpro.dao.UsuarioDAO;
import co.com.sigpro.exception.DatoException;
import co.com.sigpro.exception.LogicaException;
import co.com.sigpro.util.Log;

@Stateless
public class UsuarioEJB extends DataBaseEJB{

	private Log logger = new Log();
	
	/**
	 * Consulta usuarios segun filtros.
	 * @throws DataException 
	 * */
	public List<Usuario> consultarUsuariosPorTipoNumeroIdentificacion(final Integer idTipoDocumento,final String numeroIdentificacion) throws LogicaException{
		try{
			UsuarioDAO usuarioDAO = new UsuarioDAO(ds_consulta);
			return usuarioDAO.consultarUsuariosPorTipoNumeroIdentificacion(idTipoDocumento,numeroIdentificacion);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	}
	
	/**
	 * Consulta usuarios segun filtros.
	 * @throws DataException 
	 * */
	public List<Usuario> consultarUsuariosPorNombre(final String nombre) throws LogicaException{
		try{
			UsuarioDAO usuarioDAO = new UsuarioDAO(ds_consulta);
			return usuarioDAO.consultarUsuariosPorNombre(nombre);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	}
	
	
	/**
	 * Insertar un nuevo usuario
	 * @throws DataException 
	 * */
	public int insertarUsuario(final Usuario nuevoUsuario) throws LogicaException{
		try{
			UsuarioDAO usuarioDAO = new UsuarioDAO(ds_consulta);
			return usuarioDAO.insertarUsuario(nuevoUsuario);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	}
	
	
	/**
	 * Consultar un usuario por IdUsuario
	 * @throws DataException 
	 * */
	public Usuario consultarUsuarioPorIdUsuario(final Integer idUsuario) throws LogicaException{
		try{
			UsuarioDAO usuarioDAO = new UsuarioDAO(ds_consulta);
			return usuarioDAO.consultarUsuarioPorIdUsuario(idUsuario);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	}
	
	
	
	/**
	 * Editar un nuevo usuario
	 * @throws DataException 
	 * */
	public int editarUsuario(final Usuario usuario) throws LogicaException{
		try{
			UsuarioDAO usuarioDAO = new UsuarioDAO(ds_consulta);
			return usuarioDAO.editarUsuario(usuario);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	}
}
