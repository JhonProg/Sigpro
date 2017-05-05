package co.com.sigpro.ejb;

import javax.ejb.Stateless;

import co.com.sigpro.bean.Meta;
import co.com.sigpro.dao.MetaDAO;
import co.com.sigpro.exception.DatoException;
import co.com.sigpro.exception.LogicaException;
import co.com.sigpro.util.Log;

@Stateless
public class MetaEJB extends DataBaseEJB{

	private Log logger = new Log();
	
	public int insertarMeta(final Meta meta) throws LogicaException{
		try{
			MetaDAO metaDAO = new MetaDAO(ds_consulta);
			return metaDAO.insertarProducto(meta);
		}catch(DatoException e){
			logger.error(e.getMessage());
			throw new LogicaException(e.getMessage(),e);
		}
	}
	
	
}
