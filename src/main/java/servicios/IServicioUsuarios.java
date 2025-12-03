package servicios;

import java.time.LocalDate;
import java.util.Optional;
import dominio.Usuario;
import dominio.dto.UsuarioDTO;

public interface IServicioUsuarios {

	public Optional<String> registrarUsuario(String nombre, String apellido, String correo, String clave, LocalDate fecha, String telefono);
	
	public boolean modificarUsuario(String idUsuario, String nombre, String apellido,String correo,String clave, LocalDate fecha, String telefono);
	
	public Usuario recuperarUsuario(String idUsuario);
	
	public Optional<UsuarioDTO> iniciarSesion(String email, String clave);
}
