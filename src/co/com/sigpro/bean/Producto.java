package co.com.sigpro.bean;

import java.io.Serializable;

public class Producto implements Serializable{

	private static final long serialVersionUID = 1L;
	private int idProducto;
	private int idProductoEstado;
	private int idProductoCategoria;
	private String sku;
	private String codigoBarras;
	private String nombre;
	private String descripcion;
	private int precioUnitario;
	private int precioDescuento;
	private int cantidad;
	
	/** relaciones */
	private ProductoEstado productoEstado;
	private ProductoCategoria productoCategoria;
	
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public int getIdProductoEstado() {
		return idProductoEstado;
	}
	public void setIdProductoEstado(int idProductoEstado) {
		this.idProductoEstado = idProductoEstado;
	}
	public int getIdProductoCategoria() {
		return idProductoCategoria;
	}
	public void setIdProductoCategoria(int idProductoCategoria) {
		this.idProductoCategoria = idProductoCategoria;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(int precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public int getPrecioDescuento() {
		return precioDescuento;
	}
	public void setPrecioDescuento(int precioDescuento) {
		this.precioDescuento = precioDescuento;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public ProductoEstado getProductoEstado() {
		return productoEstado;
	}
	public void setProductoEstado(ProductoEstado productoEstado) {
		this.productoEstado = productoEstado;
	}
	public ProductoCategoria getProductoCategoria() {
		return productoCategoria;
	}
	public void setProductoCategoria(ProductoCategoria productoCategoria) {
		this.productoCategoria = productoCategoria;
	}
	
	
	
	
}
