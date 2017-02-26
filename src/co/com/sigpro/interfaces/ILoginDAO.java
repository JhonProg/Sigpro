package co.com.sigpro.interfaces;

import co.com.sigpro.bean.Usuario;
import co.com.sigpro.exception.DatoException;

/**
 * Interface de login para ususurios y session en DAO.
 * */
public interface ILoginDAO {

	public Usuario consultarUsuario(Usuario usuario) throws DatoException;
	
}
