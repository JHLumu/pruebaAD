package dominio.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class UsuarioDTO implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private String id;
	private String correo;
	private String nombre;
	private String apellidos;
	private LocalDate fechaNacimiento;
	private String telefono;
	private boolean esAdministrador;
	
	
	public UsuarioDTO(String id, String nombre, String apellidos, String correo, LocalDate fechaNacimiento, String telefono, boolean esAdministrador) {
		
		this.id  = id;
		this.correo = correo;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.esAdministrador = esAdministrador;
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public boolean isEsAdministrador() {
		return esAdministrador;
	}
	public void setEsAdministrador(boolean esAdministrador) {
		this.esAdministrador = esAdministrador;
	}
	

}
