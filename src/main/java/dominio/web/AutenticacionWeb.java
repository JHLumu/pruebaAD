package dominio.web;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dominio.dto.UsuarioDTO;
import servicios.FactoriaServicios;
import servicios.IServicioUsuarios;

/**
 * Clase utilizada como Bean de respaldo para las vistas login y signup.
 */

@SuppressWarnings("serial")
@Named
@SessionScoped
public class AutenticacionWeb implements Serializable{

	private IServicioUsuarios servicio;
	
	private String id;
	private String email;
	private String clave;
	private String confirmarClave;
	
	private String nombre;
	private String apellidos; 
	private String telefono;
	private LocalDate fecha;
	
	/*
	 * FacesContext es de ámbito request, sólo se debería inyectar para ámbitos de igual o menor tiempo de vida.
	 */
	private FacesContext facesContext;
	
	
	
	public AutenticacionWeb() {
		servicio = FactoriaServicios.getServicio(IServicioUsuarios.class);
	}
	
	public void clear() {
		id = null;
		email = null;
		clave = null;
		confirmarClave = null;
		nombre = null;
		apellidos = null;
		telefono = null;
		fecha = null;
	}
	
	public void iniciarSesion() {
		/**
		 * TODO: Revisar lógica para que, si el inicio es correcto, se obtenga la información del usuario y se muestre en el header.
		 */
		Optional<UsuarioDTO> resultado = servicio.iniciarSesion(email, clave);
		facesContext = 	FacesContext.getCurrentInstance();
		if (resultado.isPresent()) {
			id = resultado.get().getId();
			nombre = resultado.get().getNombre();
			apellidos = resultado.get().getApellidos();
			fecha = resultado.get().getFechaNacimiento();
			telefono = resultado.get().getTelefono();
			try {
				facesContext.getExternalContext().redirect("index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		else {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Credenciales incorrectas."
					+ " Por favor, revise los campos."));
			
		}
	}
	
	public void cerrarSesion() {
		String currentPath = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		facesContext = FacesContext.getCurrentInstance();
		facesContext.getExternalContext().invalidateSession();
		try {
			facesContext.getExternalContext().redirect(currentPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void registrarse() {
		Optional<String> resultado = servicio.registrarUsuario(nombre, apellidos, email, clave, confirmarClave, fecha, telefono);
		facesContext = FacesContext.getCurrentInstance();
		if (resultado.isPresent()) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Te has registrado correctamente."));
			facesContext.getExternalContext().getFlash().setKeepMessages(true);
			nombre = null;
			apellidos = null;
			clave = null;
			confirmarClave = null;
			fecha = null;
			telefono = null;
		}
		
		else {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Registro fallido."
					+ " Por favor, revise los campos."));
	}
	
}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getConfirmarClave() {
		return confirmarClave;
	}

	public void setConfirmarClave(String confirmarClave) {
		this.confirmarClave = confirmarClave;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public FacesContext getFacesContext() {
		return facesContext;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}
	
}
