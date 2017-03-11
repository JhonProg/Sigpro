package co.com.sigpro.bean;

import java.io.Serializable;

public class ProductoEstado implements Serializable{

	private static final long serialVersionUID = 1L;
	private int idpedidoestado;
	private String estado;
	private String descripcion;
	
	public int getIdpedidoestado() {
		return idpedidoestado;
	}
	public void setIdpedidoestado(int idpedidoestado) {
		this.idpedidoestado = idpedidoestado;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
