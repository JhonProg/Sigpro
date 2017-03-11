package co.com.sigpro.bean;

public class ProductoCategoria {

	private int idProductoCategoria;
	private String categoria;
	private String descripcion;
	private int estado;
		
	public int getIdProductoCategoria() {
		return idProductoCategoria;
	}
	public void setIdProductoCategoria(int idProductoCategoria) {
		this.idProductoCategoria = idProductoCategoria;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
}
