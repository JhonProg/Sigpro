package co.com.sigpro.ejb;

import javax.ejb.Stateless;

import co.com.sigpro.bean.Usuario;
import co.com.sigpro.dao.LoginDAO;
import co.com.sigpro.exception.DatoException;
import co.com.sigpro.exception.LogicaException;
import co.com.sigpro.interfaces.ILoginEJB;
import co.com.sigpro.util.Log;

@Stateless
public class LoginEJB extends DataBaseEJB implements ILoginEJB{
	
	private Log logger = new Log();
	
	/**
	 * Consulta la base de datos para validar la existencia de un usuario segun usuario y clave.
	 * @param Usuario usuario.
	 * @throws DataException 
	 * */
	@Override
	public Usuario consultarUsuario(Usuario usuario) throws LogicaException{
		try{
			LoginDAO loginDAO = new LoginDAO(ds_consulta);
			return loginDAO.consultarUsuario(usuario);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	}
	
}
