package co.com.sigpro.dao;

import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import co.com.sigpro.bean.Meta;
import co.com.sigpro.exception.DatoException;
import co.com.sigpro.util.Log;

public class MetaDAO extends DataBaseDAO {

private Log logger = new Log();
	
	
	/**
	 * Constructor.
	 * */
	public MetaDAO(){
	}
	
	/**
	 * Constructor. Recibe la conexion a la fuente de datos.
	 * @param DataSource dataSource
	 * */
	public MetaDAO(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	public int insertarProducto(Meta meta) throws DatoException {
		Integer estatus = 0;
		int columna = 1;

		try {

			StringBuffer INSERCION = new StringBuffer();
			
			INSERCION.append(" INSERT INTO meta(idusuario,idmes,valor) ");
			INSERCION.append(" VALUES(?,?,?) ");
						
			conexion = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(INSERCION.toString(),Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setInt(columna++,meta.getIdUsuario());
			preparedStatement.setInt(columna++,meta.getIdMes());
			preparedStatement.setInt(columna++,meta.getMeta());

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
