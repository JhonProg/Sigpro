package co.com.sigpro.bean;

public class PedidoPorPromotor {

	private String nombrePromotor;
	private int cantidadPedidos;
		
	public String getNombrePromotor() {
		return nombrePromotor;
	}
	
	public void setNombrePromotor(String nombrePromotor) {
		this.nombrePromotor = nombrePromotor;
	}
	
	public int getCantidadPedidos() {
		return cantidadPedidos;
	}
	
	public void setCantidadPedidos(int cantidadPedidos) {
		this.cantidadPedidos = cantidadPedidos;
	}
}
