package co.com.sigpro.dao;

import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import co.com.sigpro.bean.Cliente;
import co.com.sigpro.constante.EstadoEnum;
import co.com.sigpro.exception.DatoException;
import co.com.sigpro.util.Log;


/**
 * Clase para manejo de operaciones con los catalogos
 * */
public class ClienteDAO extends DataBaseDAO{

	private Log logger = new Log();
	
	private final String CLASS_NAME = "[ClienteDAO]";
	
	/**
	 * Constructor.
	 * */
	public ClienteDAO(){
	}
	
	/**
	 * Constructor. Recibe la conexion a la fuente de datos.
	 * @param DataSource dataSource
	 * */
	public ClienteDAO(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	public Cliente validaClientePorTipoNumeroDocumento(final Integer idTipoDocumentoCliente,final String numeroDocumentoCliente) throws DatoException{
		final String METHOD_NAME = "[consultarUsuarioPorIdUsuario]";
		Cliente cliente          = null;
				
		logger.info(CLASS_NAME+"-"+METHOD_NAME);
		
		StringBuffer CONSULTA = new StringBuffer();
		
		CONSULTA.append(" SELECT idcliente,idtipodocumento, numerodocumento, nombre, apellido, direccion, telefono, estado \r\n");
		CONSULTA.append(" FROM cliente \r\n");
		CONSULTA.append(" WHERE idtipodocumento=? \r\n");
		CONSULTA.append(" AND numerodocumento=? \r\n");
		CONSULTA.append(" AND estado=? \r\n");
		
		logger.info(CONSULTA.toString());
		
		try{
			conexion          = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(CONSULTA.toString());
			preparedStatement.setInt(1, idTipoDocumentoCliente);
			preparedStatement.setString(2, numeroDocumentoCliente);
			preparedStatement.setInt(3, EstadoEnum.ACTIVO.getIndex());
			resultSet         = preparedStatement.executeQuery();
			int columna       = 1;
			
			while(resultSet.next()){
				columna = 1;
				
				cliente = new Cliente();
				
				cliente.setIdcliente(resultSet.getInt(columna++));
				cliente.setIdTipoDocumento(resultSet.getInt(columna++));
				cliente.setNumeroDocumento(resultSet.getString(columna++));
				cliente.setNombre(resultSet.getString(columna++));
				cliente.setApellido(resultSet.getString(columna++));
				cliente.setDireccion(resultSet.getString(columna++));
				cliente.setTelefono(resultSet.getString(columna++));
				cliente.setEstado(resultSet.getInt(columna++));
								
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
		
		return cliente;
	}
	
	public int actualizarCliente(final Cliente cliente) throws DatoException {
		Integer estatus = 0;
		int columna     = 1;

		try {

			StringBuffer ACTUALIZACION = new StringBuffer();
			
			ACTUALIZACION.append(" UPDATE cliente ");
			ACTUALIZACION.append(" SET idtipodocumento=?, numerodocumento=?, nombre=?, apellido=?, direccion=?, telefono=? ");
			ACTUALIZACION.append(" WHERE idcliente=? ");
			
			logger.info(ACTUALIZACION.toString());
			
			conexion = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(ACTUALIZACION.toString());
			columna = 1;
						
			preparedStatement.setInt(columna++,cliente.getIdTipoDocumento());
			preparedStatement.setString(columna++,cliente.getNumeroDocumento());
			preparedStatement.setString(columna++,cliente.getNombre());
			preparedStatement.setString(columna++,cliente.getApellido());
			preparedStatement.setString(columna++,cliente.getDireccion());
			preparedStatement.setString(columna++,cliente.getTelefono());
			preparedStatement.setInt(columna++,cliente.getIdcliente());
			
			estatus = estatus + preparedStatement.executeUpdate();
			
			logger.info("estatus:"+estatus);
			
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
	
	
	
	public int insertarCliente(final Cliente c) throws DatoException {
		Integer estatus = 0;

		try {

			StringBuffer INSERCION = new StringBuffer();
						
			INSERCION.append(" INSERT INTO cliente(idtipodocumento, numerodocumento, nombre, apellido, direccion, telefono, estado) ");
			INSERCION.append(" VALUES("+c.getIdTipoDocumento()+",'"+c.getNumeroDocumento()+"','"+c.getNombre()+"','"+c.getApellido()+"','"+c.getDireccion()+"','"+c.getTelefono()+"',"+c.getEstado()+"); ");
			
			logger.info(INSERCION.toString());
			
			conexion = dataSource.getConnection();
			preparedStatement = conexion.prepareStatement(INSERCION.toString(),Statement.RETURN_GENERATED_KEYS);
		
			estatus = estatus + preparedStatement.executeUpdate();
			
			if(estatus==0){
				throw new DatoException("No fue posible registrar al nuevo cliente.");
			}else{
				resultSet = preparedStatement.getGeneratedKeys();
				resultSet.next();
				estatus = resultSet.getInt(1);
			}
			
			logger.info("estatus:["+estatus+"]");
			
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
