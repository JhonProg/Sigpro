package co.com.sigpro.bean;

import java.io.Serializable;

public class Usuario implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Integer idUsuario;
	private Integer idRol;
	private Integer idTipoDocumento;
	private String numeroDocumento;
	private String usuario;
	private String clave;
	private String nombre;
	private String apellido;
	private Integer estado;
	
	/** Relaciones */
	private TipoDocumento tipoDocumento;
	private Rol rol;
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public Integer getIdRol() {
		return idRol;
	}
	
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	
	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}
	
	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getClave() {
		return clave;
	}
	
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public Integer getEstado() {
		return estado;
	}
	
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", idRol=" + idRol + ", idTipoDocumento=" + idTipoDocumento
				+ ", numeroDocumento=" + numeroDocumento + ", usuario=" + usuario + ", clave=" + clave + ", nombre="
				+ nombre + ", apellido=" + apellido + ", estado=" + estado + "]";
	}

	/** Relaciones*/
	
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	
	
	
}
