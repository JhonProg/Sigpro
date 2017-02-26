package co.com.sigpro.ejb;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

@Stateless
public class DataBaseEJB {

	@Resource(mappedName = "ds_consulta")
	protected DataSource ds_consulta;
	
	/**
	@Resource(mappedName = "ds_insercion")
	protected DataSource ds_insercion;
	
	@Resource(mappedName = "ds_actualizacion")
	protected DataSource ds_actualizacion;
	
	@Resource(mappedName = "ds_eliminacion")
	protected DataSource ds_eliminacion;
	*/
}
