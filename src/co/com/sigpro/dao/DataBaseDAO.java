package co.com.sigpro.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;


import co.com.sigpro.util.Log;

/**
 * Clase para manejo de conexiones hacia la base de datos.
 * */
public class DataBaseDAO {

	private Log logger = new Log();

	protected transient Connection conexion                 = null;
	protected transient PreparedStatement preparedStatement = null;
	protected transient ResultSet resultSet                 = null;
	protected transient CallableStatement callableStatement = null;

	protected DataSource dataSource = null;

	/**
	 * Cerrar conexiones.
	 * */
	protected void closeConnections() {
		this.closeResultSet();
		this.closePreparedStatement();
		this.closeConnection();
		this.closeCallableStatement();
	}
	

	/**
	 * Cierra la conexión
	 */
	private void closeConnection() {
		if (null != conexion) {
			try {
				conexion.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
	}

	/**
	 * Cerrar conexion de sentencia preparada.
	 */
	private void closePreparedStatement() {
		if (null != preparedStatement) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
	}

	/**
	 * Cerrar conjuntos de resultados.
	 */
	private void closeResultSet() {
		if (null != resultSet) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
	}

	/**
	 * Cerrar sentencia llamable.
	 */
	private void closeCallableStatement() {
		if (null != callableStatement) {
			try {
				callableStatement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
	}
	
}
