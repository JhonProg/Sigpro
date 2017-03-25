package co.com.sigpro.ejb;


import javax.ejb.Stateless;

import co.com.sigpro.bean.Cliente;
import co.com.sigpro.dao.ClienteDAO;
import co.com.sigpro.exception.DatoException;
import co.com.sigpro.exception.LogicaException;
import co.com.sigpro.util.Log;

@Stateless
public class ClienteEJB extends DataBaseEJB{

	private Log logger = new Log();
		
	/**
	 * Consultar un usuario por IdUsuario
	 * @throws DataException 
	 * */
	public Cliente validaClientePorTipoNumeroDocumento(final Integer idTipoDocumentoCliente,final String numeroDocumentoCliente) throws LogicaException{
		try{
			ClienteDAO clienteDAO = new ClienteDAO(ds_consulta);
			return clienteDAO.validaClientePorTipoNumeroDocumento(idTipoDocumentoCliente,numeroDocumentoCliente);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	}
	
	
	public int actualizarCliente(final Cliente cliente) throws LogicaException{
		try{
			ClienteDAO clienteDAO = new ClienteDAO(ds_consulta);
			return clienteDAO.actualizarCliente(cliente);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	}
	
	public int insertarCliente(final Cliente cliente) throws LogicaException{
		try{
			ClienteDAO clienteDAO = new ClienteDAO(ds_consulta);
			return clienteDAO.insertarCliente(cliente);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	}
}
