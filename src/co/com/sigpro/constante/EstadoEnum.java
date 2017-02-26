package co.com.sigpro.constante;

public enum EstadoEnum {

	INACTIVO(1),ACTIVO(2);
	
	private int index;
	
	private EstadoEnum(int index){
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

}
