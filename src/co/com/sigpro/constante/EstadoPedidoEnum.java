package co.com.sigpro.constante;

public enum EstadoPedidoEnum {

	
	CREADO(1),
	PREPARADO(2),
	EN_VENTA(3),
	FINALIZADO(4),
	CANCELADO(5);
	
	private int index;
	
	private EstadoPedidoEnum(int index){
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
}
