package co.com.sigpro.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import co.com.sigpro.constante.SigproConstante;
import co.com.sigpro.util.Log;


@WebListener
public class AplicacionListener  implements ServletContextListener, HttpSessionListener  {

	private final String CLASS_NAME = "[AplicacionListener]";
	
	private Log logger = new Log();
	
	@Override
	public void sessionCreated(HttpSessionEvent he) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent he) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent ce) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent cei) {
		crearAplicacion(cei);
	}
	
	private void crearAplicacion(ServletContextEvent cei) {
		final String METHOD_NAME = "[crearAplicacion]";
		try{
			logger.info(CLASS_NAME+METHOD_NAME+" Cargo aplicacion : Sistema de Gestion de Productos (SGP)");
			cei.getServletContext().setAttribute(SigproConstante.APLICACION, "sgp");
		}catch(Exception e){
			logger.error("Se ha lanzado una excepcion al setar el atributo aplicacion. Mensaje:"+e.getMessage());
		}
		
	}

	
}
