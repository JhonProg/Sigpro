package co.com.sigpro.bean;

public class Pedido {

	private int idpedido;
	private int idpedidoestado;
	private int idcliente;
	private int idusuario;
	private String observacion;
	
	public int getIdpedido() {
		return idpedido;
	}
	public void setIdpedido(int idpedido) {
		this.idpedido = idpedido;
	}
	public int getIdpedidoestado() {
		return idpedidoestado;
	}
	public void setIdpedidoestado(int idpedidoestado) {
		this.idpedidoestado = idpedidoestado;
	}
	public int getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}
	public int getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
}
