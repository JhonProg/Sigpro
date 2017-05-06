package co.com.sigpro.bean;

public class VentaPorPromotor {

	private String nombrePromotor;
	private String apellidoPromotor;
	private int totalVentas;
	private int mes;
	private String mesNombre;
	private int meta;
	
	public String getNombrePromotor() {
		return nombrePromotor;
	}
	
	public void setNombrePromotor(String nombrePromotor) {
		this.nombrePromotor = nombrePromotor;
	}
	
	public int getTotalVentas() {
		return totalVentas;
	}
	
	public void setTotalVentas(int totalVentas) {
		this.totalVentas = totalVentas;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public String getMesNombre() {
		return mesNombre;
	}

	public void setMesNombre(String mesNombre) {
		this.mesNombre = mesNombre;
	}

	public int getMeta() {
		return meta;
	}

	public void setMeta(int meta) {
		this.meta = meta;
	}

	public String getApellidoPromotor() {
		return apellidoPromotor;
	}

	public void setApellidoPromotor(String apellidoPromotor) {
		this.apellidoPromotor = apellidoPromotor;
	}
	
	
	
}
