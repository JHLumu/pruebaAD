package dominio;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Representa un usuario de la plataforma.
 * Almacena información relevante como identificador, nombre, apellidos, correo electrónico, clave,
 * fecha de nacimiento, teléfono y si el usuario es administrador.
 */
@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	/** Identificador único del usuario */
	private int id;
	/** Correo electrónico del usuario */
	private String email;
	/** Nombre del usuario */
	private String nombre;
	/** Apellidos del usuario */
	private String apellidos;
	/** Clave de acceso del usuario */
	private String clave;
	/** Fecha de nacimiento del usuario */
	private LocalDate fechaNacimiento;
	/** Teléfono de contacto del usuario */
	private String telefono;
	/** Indica si el usuario tiene privilegios de administrador */
	private boolean esAdministrador;
	
	/**
	 * Constructor sin argumentos para JPA.
	 */
	public Usuario() {}
	
}