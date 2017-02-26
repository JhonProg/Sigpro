package co.com.sigpro.interfaces;

import co.com.sigpro.bean.Usuario;
import co.com.sigpro.exception.LogicaException;

/**
 * Interface para usuaris y session EJB.
 * */
public interface ILoginEJB {

	public Usuario consultarUsuario(Usuario usuario) throws LogicaException;
	
}
