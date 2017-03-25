package co.com.sigpro.bean;

public class Carrito {

	private PedidoDetalle pedidoDetalle;
	private Producto producto;
	private Pedido pedido;
	
	public PedidoDetalle getPedidoDetalle() {
		return pedidoDetalle;
	}
	public void setPedidoDetalle(PedidoDetalle pedidoDetalle) {
		this.pedidoDetalle = pedidoDetalle;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
}
